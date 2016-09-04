package Client;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.*;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.json.JSONException;
import org.json.JSONObject;

public class Client_GUI extends JFrame {
	private Container container;
	private JTextField UserNameField;
	private JButton setNameButton, sendmsgButton;
	private JTextArea msgArea, typeinArea;
	private Socket server;
	private DataInputStream input = null;
	private DataOutputStream output = null;

	public Client_GUI(Socket server) {
		super("Client_Chat");
		this.server = server;
		try {
			this.input = new DataInputStream(server.getInputStream());
			this.output = new DataOutputStream(server.getOutputStream());
			setUpFrame();
			setUpButtonAction();
			this.setVisible(true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void setUpFrame() {
		this.setSize(600, 400);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.container = this.getContentPane();
		JPanel panel = new JPanel();

		panel.setLayout(new FlowLayout(FlowLayout.LEFT));
		panel.add(new JLabel("Your Name"));
		panel.add(this.UserNameField = new JTextField(10));
		panel.add(this.setNameButton = new JButton("SetName"));

		this.msgArea = new JTextArea(13, 50);
		this.msgArea.setLineWrap(true);
		this.msgArea.setWrapStyleWord(true);
		this.msgArea.setEditable(false);
		panel.add(new JScrollPane(this.msgArea));

		this.typeinArea = new JTextArea(2, 40);
		this.typeinArea.setLineWrap(true);
		this.typeinArea.setEditable(false);
		panel.add(new JScrollPane(this.typeinArea));

		panel.add(sendmsgButton = new JButton("send"));
		this.sendmsgButton.setPreferredSize(new Dimension(100,35));
		this.sendmsgButton.setEnabled(false);

		this.container.add(panel);
	}

	private void setUpButtonAction() {
		this.setNameButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					output.writeUTF(UserNameField.getText());
					String if_exist = input.readUTF();
					if (if_exist.equals("Name Set Complete")) {
						sendmsgButton.setEnabled(true);
						typeinArea.setEditable(true);
						msgArea.append("Name_Set_Sucess\n");

						new Thread(new Runnable() {
							@Override
							public void run() {
								// TODO Auto-generated method stub
								JSONObject json_msg;
								while (true) {
									try {
										json_msg = new JSONObject(input.readUTF());
										msgArea.append(json_msg.getJSONObject("Data").getString("message") + "\n");
									} catch (JSONException | IOException e) {
										// TODO Auto-generated catch block
										//e.printStackTrace();
										//System.out.println("Disconnect Now, Close receive thread");
									}
								}
							}

						}).start();
					} else {
						msgArea.append("Name_Set_Wrong,Please try again!\n");
					}
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					//e1.printStackTrace();
				}
			}
		});
		this.sendmsgButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String json_start = "{\"Data\":{\"message\":\"", json_end = "\"}}";
					String json_msg = typeinArea.getText();
					if (json_msg != null) {
						if (json_msg.equals("bye")) {
							json_msg = json_start + json_msg + json_end;
							output.writeUTF(json_msg);
							input.close();
							output.close();
							server.close();
							System.exit(0);
						} else {
							json_msg = json_start + json_msg + json_end;
							json_msg = json_msg.replaceAll("\t", " ").replaceAll("\n", " ");
							output.writeUTF(json_msg);
							output.flush();
							typeinArea.setText("");
						}
					}

				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
	}
}
