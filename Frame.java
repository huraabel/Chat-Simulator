import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class Frame extends JFrame implements TextMaker {
	
	
	
	public JPanel panel = new JPanel();
	//public JPanel panel2 = new JPanel();
	public JTextField text = new JTextField(30);
	public JButton send = new JButton("send");
	public JButton send2 = new JButton("send2");
	public  JTextArea area = new JTextArea();
	public JComboBox combo = new JComboBox(new String[] {":)", ":(",":*",";*",">:D<"});
	public JButton emojiB = new JButton("emoji");
	
	public JScrollPane scroll = new JScrollPane(area);
	
	public HashMap<String,String> emoji = new HashMap<String,String>();
	
	
	static String check(String s) throws SwearWordException
	{
		if(  s.contains("fuck") || s.contains("cunt")  )
		{
			
			throw new SwearWordException();
		}
		return s;
	}
	
	
	public Frame(){
		super("CHAT");
		
		emoji.put(":)", "<smile>");
		emoji.put(":(","<sad>");
		emoji.put(":*","<kiss>");
		emoji.put(";*","<wink kiss>");
		emoji.put(">:D<","<hug>");
		
		
		this.setSize(700,300);
		add(panel,BorderLayout.SOUTH);
		panel.add(text);
		panel.add(send);
		panel.add(send2);
		panel.add(combo); panel.add(emojiB);
		
		
		add(scroll,BorderLayout.CENTER);
		//panel2.add(area);
		
		
		emojiB.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				String line="";
				try{
					line=check( text.getText());
					String em = emoji.get( combo.getSelectedItem().toString() );
					
					text.setText( addText(line,   em) );
				}
				catch(SwearWordException e)
				{
					line="$%#@!";
					text.setText( line );
					e.printStackTrace();
				}
				
				
			}
			
		});
		
		send.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				String line="";
				try{
					line=check( text.getText());
				}
				catch(SwearWordException e)
				{
					line="$%#@!";
					
					e.printStackTrace();
				}
				
				String app = "You: "+line + "\n";
				
				area.append(app);
				text.setText("");
			}
			
		});
		
		send2.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				String line="";
				try{
					line=check( text.getText());
				}
				catch(SwearWordException e)
				{
					line="$%#@!";
					e.printStackTrace();
				}
				
				area.append("Stranger: "+line + "\n");
				text.setText("");
			}
			
		});
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Frame frame = new Frame();
	}


	@Override
	public String addText(String s,String n) {
		s=s+" "+n;
		return s;
		
	}

}
