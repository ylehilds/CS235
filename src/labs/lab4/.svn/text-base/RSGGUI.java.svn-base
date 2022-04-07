

package cs235.rsg;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.BoxLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class RSGGUI {

    public static void main(String[] params) {

	RSG model = Factory.createRSG();
	View view = new View(model);

    }

}

class View extends JFrame {

    private RSG model;

    private JTextArea output;
    private JComboBox grammarComboBox;

    private String path = "";

    public View(RSG model) {
	super("Random Sentence Generator");

	this.model = model;

	JPanel mainPanel;
	mainPanel = new MainPanel();
	setContentPane(mainPanel);
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	pack();
	setVisible(true);

    }

    public void updateView() {

	final int MaxLen = 60;

	String grammar = (String)grammarComboBox.getSelectedItem();
	grammar = path + grammar.replace(' ', '-') + ".g";

	String s = "";

	if (model.loadGrammar(grammar)) {
	    s = model.generateSentence();
	    s = RSGDriver.formatSentence(s, MaxLen);
	} else {
            s = "Error loading the grammar: " + grammar;
	}

        output.setText(s);

    }

    class MainPanel extends JPanel {

	public MainPanel() {

	    setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
	    add(new ControlPanel());
	    add(new OutputPanel());

	}

    }


    private String[] grammars = {
	"Bionic Woman episode",
        "Bond movie",
        "Civ paper",
        "College rejection letter",
        "CS assignment handout",
        "Dear John letter",
        "Extension request",
        "Haiku",
        "How they met story",
	"Kant",
        "Poem",
        "Star Trek episode"
    };


    class ControlPanel extends JPanel {

	public ControlPanel() {

	    JButton generateButton;

	    grammarComboBox = new JComboBox(grammars);
	    grammarComboBox.addActionListener(
		new ActionListener() {
		    public void actionPerformed( ActionEvent e ) {
			updateView();
		    }
		}
					 );

	    generateButton = new JButton("Generate");
	    generateButton.addActionListener(
		new ActionListener() {
		    public void actionPerformed( ActionEvent e ) {
			updateView();
		    }
		}
					 );

	    add(grammarComboBox);
	    add(generateButton);
    
	}
    }

    class OutputPanel extends JPanel {

	public OutputPanel() {

	    final int rows = 20;
	    final int cols = 40;

	    output = new JTextArea(rows, cols);
	    output.setEditable(false);

	    //	    output.setLineWrap(true);
	    //	    output.setWrapStyleWord(true);

	    JScrollPane scroll = new JScrollPane(output);

	    add(scroll);

	}

    }

}

