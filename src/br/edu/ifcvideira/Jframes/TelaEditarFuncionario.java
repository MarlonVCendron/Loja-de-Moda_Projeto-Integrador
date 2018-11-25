package br.edu.ifcvideira.Jframes;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.ButtonUI;
import javax.swing.plaf.basic.BasicButtonUI;
import javax.swing.text.MaskFormatter;

import org.eclipse.wb.swing.FocusTraversalOnArray;

import br.edu.ifcvideira.Classes.Cliente;
import br.edu.ifcvideira.Classes.Usuario;
import br.edu.ifcvideira.DAOs.ClienteDao;
import br.edu.ifcvideira.DAOs.UsuarioDao;
import br.edu.ifcvideira.DAOs.VendaDao;
import br.edu.ifcvideira.utils.Cor;
import br.edu.ifcvideira.utils.Preferencias;
import br.edu.ifcvideira.utils.Senha;

import javax.swing.Icon;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class TelaEditarFuncionario extends JFrame {

	private JPanel contentPane;

	private JTextField tfCelular;
	private JTextField tfTelefone;
	private JTextField tfCpf;
	private JTextField tfNome;
	private JTextField tfRg;
	private JPanel panelImgCpf;
	private JPanel panelImgTelefone;
	private JPanel panelImgRg;
	private JPanel panelImgEmail;

	JComboBox cbTipo = new JComboBox();

	JComboBox cbStatus = new JComboBox();

	public static JLabel lblLogo;

	Color corTexto = new Color(75, 80, 85);
	Color corGeral = new Color(Preferencias.getR(), Preferencias.getG(), Preferencias.getB());
	Color corSecundaria = Cor.corMaisClara(corGeral, 0.2f);
	Color corTerciaria = Cor.corMaisClara(corGeral, 0.4f);
	Color corVermelho = new Color(230, 20, 20);

	Point posMouseInicial;
	boolean[] camposCorretos = { false, false, false, false };

	JSeparator spRg = new JSeparator();

	JSeparator spTelefone = new JSeparator();

	JSeparator spCpf = new JSeparator();

	JSeparator spNome = new JSeparator();
	JSeparator spEmail = new JSeparator();

	Usuario us = new Usuario();
	private static UsuarioDao usDao = new UsuarioDao();

	ImageIcon imageIconX = new ImageIcon(TelaLogin.class.getResource("/br/edu/ifcvideira/img/xerro.png"));
	Image imagemX = imageIconX.getImage().getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH);
	private JTextField tfEmail;
	private JTextField tfData;
	private JTextField tfId;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaEditarFuncionario frame = new TelaEditarFuncionario(null,1);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TelaEditarFuncionario(Usuario usuario, int idUsuarioAtual) {
		setName("Tela Cadastro Cliente");
		Dimension tela = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		int largura = 512;
		int altura = 700;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds((tela.width / 2) - (largura / 2), (tela.height / 2) - (altura / 2), largura, altura);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setUndecorated(true);
		contentPane.setLayout(null);
		setResizable(false);
		getRootPane().setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, corGeral));

		ImageIcon imageIconLogo = new ImageIcon(Preferencias.getImagem());
		Image imagemLogo = imageIconLogo.getImage().getScaledInstance(300, 300, java.awt.Image.SCALE_SMOOTH);
		setIconImage(imagemLogo);

		JPanel panelSuperior = new JPanel();
		panelSuperior.setBounds(0, 0, 512, 32);
		contentPane.add(panelSuperior);
		panelSuperior.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				posMouseInicial = e.getPoint();
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				posMouseInicial = null;
			}
		});
		panelSuperior.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent arg0) {
				Point posMouseAtual = arg0.getLocationOnScreen();
				setLocation(posMouseAtual.x - posMouseInicial.x, posMouseAtual.y - posMouseInicial.y);
			}
		});
		panelSuperior.setBackground(new Color(255, 255, 255));
		panelSuperior.setLayout(null);

		JButton btnX = new JButton("X");
		btnX.setUI((ButtonUI) BasicButtonUI.createUI(btnX));
		btnX.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				btnX.setBackground(corSecundaria);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnX.setBackground(corGeral);
			}

			@Override
			public void mousePressed(MouseEvent e) {
				btnX.setBackground(corTerciaria);
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				btnX.setBackground(corGeral);
			}
		});
		btnX.setBackground(corGeral);
		btnX.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		btnX.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnX.setBounds(470, 0, 42, 30);
		btnX.setMaximumSize(new Dimension(80, 50));
		btnX.setFont(new Font("Roboto", Font.PLAIN, 13));
		btnX.setBorder(null);
		panelSuperior.add(btnX);

		JButton btnMinimizar = new JButton("-");
		btnMinimizar.setUI((ButtonUI) BasicButtonUI.createUI(btnMinimizar));
		btnMinimizar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				btnMinimizar.setBackground(corSecundaria);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnMinimizar.setBackground(corGeral);
			}

			@Override
			public void mousePressed(MouseEvent e) {
				btnMinimizar.setBackground(corTerciaria);
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				btnMinimizar.setBackground(corGeral);
			}
		});
		btnMinimizar.setBackground(corGeral);
		btnMinimizar.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		btnMinimizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setState(JFrame.ICONIFIED);
			}
		});
		btnMinimizar.setMaximumSize(new Dimension(80, 50));
		btnMinimizar.setFont(new Font("Roboto", Font.PLAIN, 15));
		btnMinimizar.setBorder(null);
		btnMinimizar.setBounds(424, 0, 42, 30);
		panelSuperior.add(btnMinimizar);

		JPanel panelCadastro = new JPanel();
		panelCadastro.setBackground(new Color(255, 255, 255));
		panelCadastro.setBounds(0, 32, 512, 700);
		contentPane.add(panelCadastro);
		panelCadastro.setLayout(null);

		JLabel lblEmail2 = new JLabel("Email");
		lblEmail2.setBounds(76, 117, 119, 42);
		lblEmail2.setFont(new Font("Roboto", Font.PLAIN, 20));
		lblEmail2.setForeground(corTexto);
		panelCadastro.add(lblEmail2);

		JLabel lblCpf = new JLabel("CPF");
		lblCpf.setBounds(76, 172, 119, 42);
		lblCpf.setForeground(corTexto);
		lblCpf.setFont(new Font("Roboto", Font.PLAIN, 20));
		panelCadastro.add(lblCpf);

		JLabel lblTelefone = new JLabel("Telefone");
		lblTelefone.setBounds(76, 227, 119, 42);
		lblTelefone.setForeground(corTexto);
		lblTelefone.setFont(new Font("Roboto", Font.PLAIN, 20));
		panelCadastro.add(lblTelefone);

		JLabel lblCelular = new JLabel("Celular");
		lblCelular.setBounds(76, 282, 163, 42);
		lblCelular.setForeground(corTexto);
		lblCelular.setFont(new Font("Roboto", Font.PLAIN, 20));
		panelCadastro.add(lblCelular);

		spNome.setBounds(238, 108, 215, 2);
		spNome.setBackground(corGeral);
		panelCadastro.add(spNome);

		spCpf.setBounds(238, 216, 215, 2);
		spCpf.setBackground(corGeral);
		panelCadastro.add(spCpf);

		spTelefone.setBounds(238, 271, 215, 2);
		spTelefone.setBackground(corGeral);
		panelCadastro.add(spTelefone);

		JSeparator spCelular = new JSeparator();
		spCelular.setBounds(238, 326, 215, 2);
		spCelular.setBackground(corGeral);
		panelCadastro.add(spCelular);

		tfNome = new JTextField();
		tfNome.setEditable(false);
		tfNome.setText(usuario.getNome());
		tfNome.setBounds(238, 76, 215, 32);
		tfNome.setForeground(corTexto);
		tfNome.setFont(new Font("Roboto", Font.PLAIN, 18));
		tfNome.setBorder(null);
		tfNome.setBackground(panelCadastro.getBackground());
	

		spRg.setBackground(new Color(176, 176, 176));
		spRg.setBounds(238, 381, 215, 2);
		panelCadastro.add(spRg);

		spEmail.setBackground(new Color(176, 176, 176));
		spEmail.setBounds(238, 161, 215, 2);
		panelCadastro.add(spEmail);
		
		JSeparator spData = new JSeparator();
		spData.setBackground(new Color(176, 176, 176));
		spData.setBounds(139, 43, 167, 2);
		panelCadastro.add(spData);
		
		JSeparator spId = new JSeparator();
		spId.setBackground(new Color(176, 176, 176));
		spId.setBounds(438, 43, 15, 2);
		panelCadastro.add(spId);
		panelCadastro.add(tfNome);

		try {
			MaskFormatter tfCpfFormatter = new MaskFormatter("###.###.###-##");
			tfCpfFormatter.setPlaceholderCharacter('_');
			tfCpf = new JFormattedTextField(tfCpfFormatter);
			tfCpf.setBounds(238, 184, 215, 32);
			tfCpf.setText(usuario.getCpf());
			tfCpf.setForeground(corTexto);
			tfCpf.setFont(new Font("Roboto", Font.PLAIN, 18));
			tfCpf.setBorder(null);
			tfCpf.setBackground(panelCadastro.getBackground());
		} catch (Exception e) {
		}
		tfCpf.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				String numerosCpf = tfCpf.getText();
				numerosCpf = numerosCpf.replaceAll("[^\\d]", "");
				camposCorretos[1] = (numerosCpf.length() < 11) ? false : true;
				spCpf.setBackground(corGeral);

				if (camposCorretos[1]) {
					spCpf.setBackground(corGeral);
					panelImgCpf.setVisible(false);
				} else {
					spCpf.setBackground(corVermelho);
					panelImgCpf.setVisible(true);
				}
			}

			@Override
			public void focusGained(FocusEvent arg0) {
				spCpf.setBackground(corGeral);
			}
		});
		panelCadastro.add(tfCpf);

		try {
			MaskFormatter tfRgFormatter = new MaskFormatter("#.###.###");
			tfRgFormatter.setPlaceholderCharacter('_');
		} catch (Exception e) {
		}

		try {
			MaskFormatter tfTelefoneFormatter = new MaskFormatter("(##) ####-####");
			tfTelefoneFormatter.setPlaceholderCharacter('_');
			tfTelefone = new JFormattedTextField(tfTelefoneFormatter);
			tfTelefone.setText(usuario.getTelefone());
			tfTelefone.setBounds(238, 239, 215, 32);
			tfTelefone.setForeground(corTexto);
			tfTelefone.setFont(new Font("Roboto", Font.PLAIN, 18));
			tfTelefone.setBorder(null);
			tfTelefone.setBackground(panelCadastro.getBackground());
		} catch (Exception e) {
		}
		tfTelefone.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				String numerosTelefone = tfTelefone.getText();
				numerosTelefone = numerosTelefone.replaceAll("[^\\d]", "");
				camposCorretos[2] = (numerosTelefone.length() < 10) ? false : true;
				spTelefone.setBackground(corGeral);

				if (camposCorretos[2]) {
					spTelefone.setBackground(corGeral);
					panelImgTelefone.setVisible(false);
				} else {
					spTelefone.setBackground(corVermelho);
					panelImgTelefone.setVisible(true);
				}
			}

			@Override
			public void focusGained(FocusEvent arg0) {
				spTelefone.setBackground(corGeral);
			}
		});
		panelCadastro.add(tfTelefone);

		try {
			MaskFormatter tfCelularFormatter = new MaskFormatter("(##) #####-####");
			tfCelularFormatter.setPlaceholderCharacter('_');
			tfCelular = new JFormattedTextField(tfCelularFormatter);
			tfCelular.setBounds(238, 294, 215, 32);
			tfCelular.setText(usuario.getCelular());
			tfCelular.setForeground(corTexto);
			tfCelular.setFont(new Font("Roboto", Font.PLAIN, 18));
			tfCelular.setBorder(null);
			tfCelular.setBackground(panelCadastro.getBackground());
		} catch (Exception e) {
		}
		tfCelular.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				String numerosCelular = tfCelular.getText();
				numerosCelular = numerosCelular.replaceAll("[^\\d]", "");
				if (numerosCelular.length() < 11) {
					tfCelular.setText("");
				}
				spCelular.setBackground(corGeral);
			}

			@Override
			public void focusGained(FocusEvent arg0) {
				spCelular.setBackground(corGeral);
			}
		});
		panelCadastro.add(tfCelular);

		try {
			MaskFormatter tfRgFormatter = new MaskFormatter("#.###.###");
			tfRgFormatter.setPlaceholderCharacter('_');
			tfRg = new JFormattedTextField(tfRgFormatter);
			tfRg.setForeground(corTexto);
			tfRg.setFont(new Font("Roboto", Font.PLAIN, 18));
			tfRg.setText(usuario.getRg());
			tfRg.setBorder(null);
			tfRg.setBackground(panelCadastro.getBackground());
		} catch (Exception e) {
		}
		tfRg.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				String numerosRg = tfRg.getText();
				numerosRg = numerosRg.replaceAll("[^\\d]", "");
				camposCorretos[3] = (numerosRg.length() < 7) ? false : true;
				spRg.setBackground(corGeral);

				if (camposCorretos[3]) {
					spRg.setBackground(corGeral);
					panelImgRg.setVisible(false);
				} else {
					spRg.setBackground(corVermelho);
					panelImgRg.setVisible(true);
				}
			}

			@Override
			public void focusGained(FocusEvent arg0) {
				spRg.setBackground(corGeral);
			}
		});
		tfRg.setColumns(10);
		tfRg.setBounds(238, 349, 215, 32);
		panelCadastro.add(tfRg);

		tfEmail = new JTextField();
		tfEmail.setText(usuario.getEmail());
		tfEmail.setForeground(new Color(75, 80, 85));
		tfEmail.setFont(new Font("Roboto", Font.PLAIN, 18));
		tfEmail.setBorder(null);
		tfEmail.setBackground(Color.WHITE);
		tfEmail.setBounds(238, 129, 215, 32);
		panelCadastro.add(tfEmail);

		tfEmail.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				camposCorretos[6] = (tfEmail.getText()
						.matches("^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5})$")) ? true : false;

				if (tfEmail.getText().length() < 1) {
					camposCorretos[6] = false;
				}

				if (camposCorretos[6]) {
					spEmail.setBackground(corGeral);
					panelImgEmail.setVisible(false);

				} else {
					spEmail.setBackground(corVermelho);
					panelImgEmail.setVisible(true);
				}
			}

			@Override
			public void focusGained(FocusEvent arg0) {
				spEmail.setBackground(corGeral);
				panelImgEmail.setVisible(false);

			}
		});
		
		tfData = new JTextField();
		tfData.setText(usuario.getDataCadastro().toString());
		tfData.setForeground(new Color(75, 80, 85));
		tfData.setFont(new Font("Roboto", Font.PLAIN, 14));
		tfData.setEditable(false);
		tfData.setBorder(null);
		tfData.setBackground(Color.WHITE);
		tfData.setBounds(139, 14, 167, 29);
		panelCadastro.add(tfData);
		
		tfId = new JTextField();
		tfId.setText(String.valueOf(usuario.getId()));
		tfId.setForeground(new Color(75, 80, 85));
		tfId.setFont(new Font("Roboto", Font.PLAIN, 14));
		tfId.setEditable(false);
		tfId.setBorder(null);
		tfId.setBackground(Color.WHITE);
		tfId.setBounds(438, 14, 15, 29);
		panelCadastro.add(tfId);

		cbTipo.setModel(new DefaultComboBoxModel(new String[] { "Caixa - Tipo 0", "Estoque - Tipo 1", "Gerente - Tipo 2" }));
		cbTipo.setForeground(new Color(75, 80, 85));
		cbTipo.setFont(new Font("Roboto", Font.PLAIN, 18));
		cbTipo.setBackground(Color.WHITE);
		cbTipo.setBounds(238, 406, 215, 32);
		cbTipo.setSelectedIndex(usuario.getTipo());
		panelCadastro.add(cbTipo);
		
		
		cbStatus.setModel(new DefaultComboBoxModel(new String[] { "Inativo", "Ativo" }));
		cbStatus.setEnabled((idUsuarioAtual == usuario.getId()) ? false : true);
		cbStatus.setForeground(new Color(75, 80, 85));
		cbStatus.setSelectedIndex(usuario.getStatus());
		cbStatus.setFont(new Font("Roboto", Font.PLAIN, 18));
		cbStatus.setBackground(Color.WHITE);
		cbStatus.setBounds(238, 463, 215, 32);
		panelCadastro.add(cbStatus);

		JLabel lblRg = new JLabel("RG");
		lblRg.setForeground(new Color(75, 80, 85));
		lblRg.setFont(new Font("Roboto", Font.PLAIN, 20));
		lblRg.setBounds(76, 337, 119, 42);
		panelCadastro.add(lblRg);

		JLabel lblTipo = new JLabel("Tipo");
		lblTipo.setForeground(new Color(75, 80, 85));
		lblTipo.setFont(new Font("Roboto", Font.PLAIN, 20));
		lblTipo.setBounds(76, 392, 119, 42);
		panelCadastro.add(lblTipo);

		JLabel lblStatus = new JLabel("Status");
		lblStatus.setForeground(new Color(75, 80, 85));
		lblStatus.setFont(new Font("Roboto", Font.PLAIN, 20));
		lblStatus.setBounds(76, 449, 119, 42);
		panelCadastro.add(lblStatus);

		JLabel lblNome = new JLabel("Nome");
		lblNome.setForeground(new Color(75, 80, 85));
		lblNome.setFont(new Font("Roboto", Font.PLAIN, 20));
		lblNome.setBounds(76, 68, 119, 42);
		panelCadastro.add(lblNome);

		JLabel imgXCpf = new JLabel(new ImageIcon(imagemX));
		imgXCpf.setBackground(new Color(255, 255, 255));
		imgXCpf.setToolTipText("O CPF é obrigatório");

		JLabel imgXTelefone = new JLabel(new ImageIcon(imagemX));
		imgXTelefone.setBackground(new Color(255, 255, 255));
		imgXTelefone.setToolTipText("O Telefone é obrigatório");

		JLabel imgXRua = new JLabel(new ImageIcon(imagemX));
		imgXRua.setBackground(new Color(255, 255, 255));
		imgXRua.setToolTipText("O RG \u00E9 obrigat\u00F3rio");

		JLabel imgXEmail = new JLabel(new ImageIcon(imagemX));
		imgXEmail.setBackground(new Color(255, 255, 255));
		imgXEmail.setToolTipText("O email é obrigatorio e deve ser preenchido corretamente");

		panelImgCpf = new JPanel();
		panelImgCpf.setVisible(false);
		
		JLabel lblData = new JLabel("Data");
		lblData.setForeground(new Color(75, 80, 85));
		lblData.setFont(new Font("Roboto", Font.PLAIN, 15));
		lblData.setBounds(76, 11, 37, 42);
		panelCadastro.add(lblData);
		
		JLabel lblId = new JLabel("Id");
		lblId.setForeground(new Color(75, 80, 85));
		lblId.setFont(new Font("Roboto", Font.PLAIN, 15));
		lblId.setBounds(408, 11, 37, 42);
		panelCadastro.add(lblId);
		panelImgCpf.setBackground(new Color(255, 255, 255));
		panelImgCpf.setBounds(39, 180, 32, 32);
		panelCadastro.add(panelImgCpf);
		panelImgCpf.add(imgXCpf);

		panelImgTelefone = new JPanel();
		panelImgTelefone.setVisible(false);
		panelImgTelefone.setBackground(new Color(255, 255, 255));
		panelImgTelefone.setBounds(39, 235, 32, 32);
		panelCadastro.add(panelImgTelefone);
		panelImgTelefone.add(imgXTelefone);

		panelImgRg = new JPanel();
		panelImgRg.setVisible(false);
		panelImgRg.setBackground(new Color(255, 255, 255));
		panelImgRg.setBounds(39, 343, 32, 32);
		panelCadastro.add(panelImgRg);
		panelImgRg.add(imgXRua);

		panelImgEmail = new JPanel();
		panelImgEmail.setVisible(false);
		panelImgEmail.setBackground(new Color(255, 255, 255));
		panelImgEmail.setBounds(39, 125, 32, 32);
		panelCadastro.add(panelImgEmail);
		panelImgEmail.add(imgXEmail);

		JButton btnEditar = new JButton("Editar");
		btnEditar.setUI((ButtonUI) BasicButtonUI.createUI(btnEditar));
		btnEditar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				btnEditar.setBackground(corSecundaria);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnEditar.setBackground(corGeral);
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				btnEditar.setBackground(corTerciaria);
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				btnEditar.setBackground(corGeral);
			}
		});
		btnEditar.setBounds(154, 600, 215, 54);
		panelCadastro.add(btnEditar);
		btnEditar.setBackground(corGeral);
		btnEditar.setBorder(null);
		btnEditar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				atualizaCamposPro();

				if (camposEstaoCorretos(camposCorretos)) {
					us.setId(usuario.getId());
					us.setCpf(tfCpf.getText());
					us.setRg(tfRg.getText());
					us.setTelefone(tfTelefone.getText());
					us.setCelular(tfCelular.getText());
					us.setStatus(cbStatus.getSelectedIndex());
					us.setTipo(cbTipo.getSelectedIndex());
					us.setEmail(tfEmail.getText());
					
					try {
						usDao.AlterarUsuario(us);
						dispose();
					} catch (Exception e) {
						e.printStackTrace();
					}

				} else {
					JOptionPane.showMessageDialog(null, "Um ou mais campos não foram preenchidos corretamente", "Erro",
							JOptionPane.ERROR_MESSAGE);
				}

			}
		});
		btnEditar.setFont(new Font("Roboto", Font.PLAIN, 18));
		btnEditar.setForeground(corTexto);

		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.setUI((ButtonUI) BasicButtonUI.createUI(btnExcluir));
		btnExcluir.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				btnExcluir.setBackground(corSecundaria);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnExcluir.setBackground(corGeral);
			}
			@Override
			public void mousePressed(MouseEvent arg0) {
				btnExcluir.setBackground(corTerciaria);
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				btnExcluir.setBackground(corGeral);
			}
		});
		
		
	}

	private void atualizaCamposPro() {
		
		String numerosCpf = tfCpf.getText();
		numerosCpf = numerosCpf.replaceAll("[^\\d]", "");
		camposCorretos[0] = (numerosCpf.length() < 11) ? false : true;
		spCpf.setBackground(corGeral);

		if (camposCorretos[0]) {
			spCpf.setBackground(corGeral);
			panelImgCpf.setVisible(false);
		} else {
			spCpf.setBackground(corVermelho);
			panelImgCpf.setVisible(true);
		}

		String numerosTelefone = tfTelefone.getText();
		numerosTelefone = numerosTelefone.replaceAll("[^\\d]", "");
		camposCorretos[1] = (numerosTelefone.length() < 10) ? false : true;
		spTelefone.setBackground(corGeral);

		if (camposCorretos[1]) {
			spTelefone.setBackground(corGeral);
			panelImgTelefone.setVisible(false);
		} else {
			spTelefone.setBackground(corVermelho);
			panelImgTelefone.setVisible(true);
		}
		String numerosRg = tfRg.getText();
		numerosRg = numerosRg.replaceAll("[^\\d]", "");
		camposCorretos[2] = (numerosRg.length() < 7) ? false : true;
		spRg.setBackground(corGeral);

		if (camposCorretos[2]) {
			spRg.setBackground(corGeral);
			panelImgRg.setVisible(false);
		} else {
			spRg.setBackground(corVermelho);
			panelImgRg.setVisible(true);
		}

		camposCorretos[3] = (tfEmail.getText()
				.matches("^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5})$")) ? true : false;

		if (tfEmail.getText().length() < 1) {
			camposCorretos[3] = false;
		}

		if (camposCorretos[3]) {
			spEmail.setBackground(corGeral);
			panelImgEmail.setVisible(false);

		} else {
			spEmail.setBackground(corVermelho);
			panelImgEmail.setVisible(true);
		}

	}

	boolean camposEstaoCorretos(boolean[] camposCorretos) {
		boolean x = true;
		for (boolean a : camposCorretos) {
			x = x && a;
		}
		return x;
	}
}
