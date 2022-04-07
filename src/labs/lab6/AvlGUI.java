

package cs235.avl;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.ButtonGroup;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

import java.util.List;
import java.util.LinkedList;
import java.util.Set;
import java.util.TreeSet;
import java.util.Stack;

public class AvlGUI {

    public static void main(String[] params) {

	Model model = new Model();
	View view = new View(model);

    }
}

class Model {

    private Set words;
    private Tree tree;

    private Words queue;

    private Stack undo;
    private Stack redo;

    Model() {
	tree = TreeFactory.getTree();
	if (tree == null)
	    System.out.println("AvlGUI: An error occured. Please make sure " + 
			       "that TreeFactory.getTree() returns an object " +
			       "of type Tree instead of null");
	queue = new Words();
	words = new TreeSet();

	undo = new Stack();
	redo = new Stack();
    }

    int reportedSize() {
	return tree.size();
    }

    int size() {
	return words.size();
    }

    void clear() {
	clearTree();
	clearQueue();
	undo.clear();
	redo.clear();
    }

    BinaryTreeNode getRootNode() {
	return tree.getRootNode();
    }

    void clearQueue() {
	queue.clear();
    }
    boolean wordsEmpty() {
	return queue.isEmpty();
    }
    String getWord() {
	return queue.getWord();
    }
    String nextWord() {
	return queue.nextWord();
    }
    void loadWords(String filename) {
	queue.loadWords(filename);
    }

    void pushCopy(Stack s, Tree t, Set words) {
	t = TreeFactory.copyTree(t);
	if (t != null) {
	    s.push(t);
	    s.push(new TreeSet(words));
	}
    }

    void clearTree() {
	if (size() > 0)
	    pushCopy(undo, tree, words);
	tree.clear();
	words.clear();
    }

    void addWord(String word, boolean doUndo) {
	if (doUndo && !words.contains(word))
	    pushCopy(undo, tree, words);
	tree.add(word);
	words.add(word);
    }
    void removeWord(String word, boolean doUndo) {
	if (doUndo && words.contains(word))
	    pushCopy(undo, tree, words);
	tree.remove(word);
	words.remove(word);
    }

    void addAll() {
	pushCopy(undo, tree, words);
	while (!queue.isEmpty())
	    addWord(queue.nextWord(), false);
    }
    void removeAll() {
	pushCopy(undo, tree, words);
	while (!queue.isEmpty())
	    removeWord(queue.nextWord(), false);
    }

    void undo() {
	redo.push(tree);
	redo.push(words);
	words = (Set)undo.pop();
	tree = (Tree)undo.pop();
    }

    void redo() {
	undo.push(tree);
	undo.push(words);
	words = (Set)redo.pop();
	tree = (Tree)redo.pop();
    }

    boolean canUndo() {
	return !undo.empty();
    }

    boolean canRedo() {
	return !redo.empty();
    }

    class Words {
	private List words;

	public Words() {
	    words = new LinkedList();
	}

	public boolean isEmpty() {
	    return words.isEmpty();
	}

	public void clear() {
	    words.clear();
	}

	public String getWord() {
	    if (words.isEmpty())
		return "";
	    else
		return (String)words.get(0);
	}

	public String nextWord() {
	    if (words.isEmpty())
		return "";
	    else
		return (String)words.remove(0);
	}

	public void loadWords(String filename) {
	    clear();
	    if(filename == null)
		throw new IllegalArgumentException();
	    try {
		BufferedReader file = new BufferedReader(new FileReader(filename));
		String word = file.readLine();
		while(word != null) {
		    words.add(word);
		    word = file.readLine();
		}
		file.close();
	    }
	    catch(IOException e) {
		throw new IllegalArgumentException();
	    }
	}

    }
}

class View extends JFrame {

    private final Color perimeterColor = new Color(159,205,159);
    private final Color windowColor = new Color(204,255,204);

    private Model model;
    private JPanel mainPanel;
    private JPanel treePanel;
    private FilePanel filePanel;
    private WordPanel wordPanel;
    private UndoPanel undoPanel;
    private HeightPanel heightPanel;
    private JScrollPane scrollpane;

    public View(Model model) {
	super("Avl Debugger");

	this.model = model;

	mainPanel = new MainPanel();
	setContentPane(mainPanel);
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	pack();
	setVisible(true);

    }

    public void updateView() {

	wordPanel.focusWordField();

	filePanel.setFileField("");
	wordPanel.setWordField(model.getWord());

	wordPanel.setButtons();
	filePanel.setButtons();
	undoPanel.setButtons();
	setMessage();

	treePanel.revalidate();
	treePanel.repaint();

    }

    public void setMessage() {
	if (model.size() == model.reportedSize())
	    setTitle("Avl Debugger - Tree contains " + model.size() + " words");
	else
	    setTitle("Avl Debugger - Size Error - Reported size: " +
		     model.reportedSize() + ", Actual size: " + model.size());
    }

    class MainPanel extends JPanel {

	public MainPanel() {
	    setLayout(new BorderLayout());
	    add(new ControlPanel(), BorderLayout.NORTH);

	    add(new EmptyPanel(), BorderLayout.SOUTH);
	    add(new EmptyPanel(), BorderLayout.EAST);
	    add(new EmptyPanel(), BorderLayout.WEST);

	    treePanel = new TreePanel();
	    scrollpane = new JScrollPane(treePanel);
	    add(scrollpane, BorderLayout.CENTER);

	}

    }

    class EmptyPanel extends JPanel {
	public EmptyPanel() {
	    setBackground(perimeterColor);
	}
    }

    class ControlPanel extends JPanel {

	public ControlPanel() {

	    setBackground(perimeterColor);
	    setLayout(new BorderLayout());

	    JPanel north = new JPanel();
	    JPanel south = new JPanel();
	    north.setBackground(perimeterColor);
	    south.setBackground(perimeterColor);

	    filePanel = new FilePanel();
	    heightPanel = new HeightPanel();
	    north.add(filePanel);
	    north.add(heightPanel);

	    wordPanel = new WordPanel();
	    undoPanel = new UndoPanel();
	    south.add(wordPanel);
	    south.add(undoPanel);

	    add(north, BorderLayout.NORTH);
	    add(south, BorderLayout.SOUTH);

	}
    }

    class WordPanel extends JPanel {

	private JLabel wordLabel;
	private JTextField wordField;

	private JRadioButton addBox;
	private JRadioButton removeBox;
	private ButtonGroup oneGroup;

	private JButton clearButton;

	void setWordField(String word) {
	    wordField.setText(word);
	}
	String getWordField() {
	    return wordField.getText();
	}
	void focusWordField() {
	    wordField.requestFocus();
	}

	public WordPanel() {

	    setBackground(perimeterColor);

	    final int fieldSize = 10;

	    wordLabel = new JLabel("Word: ");
	    wordField = new JTextField(fieldSize);
	    wordField.addActionListener(
		new ActionListener() {
		    public void actionPerformed( ActionEvent e ) {
			wordAction();
		    }
		}
					);

	    addBox = new JRadioButton("Add Word");
	    addBox.setBackground(perimeterColor);
	    addBox.addActionListener(
		new ActionListener() {
		    public void actionPerformed( ActionEvent e ) {
			focusWordField();
		    }
		}
				     );

	    removeBox = new JRadioButton("Remove Word");
	    removeBox.setBackground(perimeterColor);
	    removeBox.addActionListener(
		new ActionListener() {
		    public void actionPerformed( ActionEvent e ) {
			focusWordField();
		    }
		}
					);

	    oneGroup = new ButtonGroup();
	    oneGroup.add(addBox);
	    oneGroup.add(removeBox);
	    addBox.setSelected(true);

	    clearButton = new JButton("Clear Tree");
	    clearButton.setMnemonic(KeyEvent.VK_C);
	    clearButton.addActionListener(
		new ActionListener() {
		    public void actionPerformed( ActionEvent e ) {
			model.clearTree();
			updateView();
		    }
		}
					  );

	    add(wordLabel);
	    add(wordField);

	    add(addBox);
	    add(removeBox);

	    add(clearButton);

	    setButtons();

	}

	private void wordAction() {
	    String word = wordField.getText();
	    if(word.equals(""))
		return;

	    if (addBox.isSelected())
		model.addWord(word, true);
	    else
		model.removeWord(word, true);

	    model.nextWord();
	    updateView();

	}

	void setButtons() {
	    clearButton.setEnabled(model.size() > 0);
	}

    }

    class FilePanel extends JPanel {

	private JLabel fileLabel;
	private JTextField fileField;

	private JButton addAllButton;
	private JButton removeAllButton;
	private JButton clearQueueButton;

	void setFileField(String file) {
	    fileField.setText(file);
	}
	void focusFileField() {
	    fileField.requestFocus();
	}
	void setButtons() {
	    addAllButton.setEnabled(!model.wordsEmpty());
	    removeAllButton.setEnabled(!model.wordsEmpty());
	    clearQueueButton.setEnabled(!model.wordsEmpty());
	}

	public FilePanel() {

	    setBackground(perimeterColor);

	    final int fieldSize = 10;

	    fileLabel = new JLabel("File: ");
	    fileField = new JTextField(fieldSize);
	    fileField.addActionListener(
		new ActionListener() {
		    public void actionPerformed( ActionEvent e ) {
			fileAction();
		    }
		}
					);

	    addAllButton = new JButton("Add All Words");
	    removeAllButton = new JButton("Remove All Words");
	    clearQueueButton = new JButton("Clear Queue");

	    addAllButton.setMnemonic(KeyEvent.VK_A);
	    removeAllButton.setMnemonic(KeyEvent.VK_R);

	    addAllButton.addActionListener(
		new ActionListener() {
		    public void actionPerformed( ActionEvent e ) {
			model.addAll();
			updateView();
			focusFileField();
		    }
		}
					   );
	    removeAllButton.addActionListener(
		new ActionListener() {
		    public void actionPerformed( ActionEvent e ) {
			model.removeAll();
			updateView();
			focusFileField();
		    }
		}
					   );
	    clearQueueButton.addActionListener(
		new ActionListener() {
		    public void actionPerformed( ActionEvent e ) {
			model.clearQueue();
			updateView();
			focusFileField();
		    }
		}
					   );

	    add(fileLabel);
	    add(fileField);

	    add(addAllButton);
	    add(removeAllButton);
	    add(clearQueueButton);

	    setButtons();
	    focusFileField();

	}

	private void fileAction() {
	    String file = fileField.getText();
	    if(file.equals(""))
		return;

	    model.loadWords(file);
	    updateView();
	}

    }

    class UndoPanel extends JPanel {

	private JButton undoButton;
	private JButton redoButton;
	private JButton clearButton;

	public UndoPanel() {

	    setBackground(perimeterColor);

	    undoButton = new JButton("Undo");
	    undoButton.setMnemonic(KeyEvent.VK_U);
	    undoButton.addActionListener(
		new ActionListener() {
		    public void actionPerformed( ActionEvent e ) {
			model.undo();
			updateView();
		    }
		}
					 );

	    redoButton = new JButton("Redo");
	    redoButton.setMnemonic(KeyEvent.VK_D);
	    redoButton.addActionListener(
		new ActionListener() {
		    public void actionPerformed( ActionEvent e ) {
			model.redo();
			updateView();
		    }
		}
					 );

	    clearButton = new JButton("Clear All");
	    clearButton.addActionListener(
		new ActionListener() {
		    public void actionPerformed( ActionEvent e ) {
			model.clear();
			updateView();
		    }
		}
					     );

	    add(undoButton);
	    add(redoButton);
	    add(clearButton);

	    setButtons();
    
	}

	void setButtons() {
	    undoButton.setEnabled(model.canUndo());
	    redoButton.setEnabled(model.canRedo());
	    clearButton.setEnabled(model.canUndo() || model.canRedo());
	}

    }

    class HeightPanel extends JPanel {

	private JCheckBox showHeightsBox;

	public HeightPanel() {

	    setBackground(perimeterColor);

	    showHeightsBox = new JCheckBox("Show Heights");
	    showHeightsBox.setBackground(perimeterColor);
	    showHeightsBox.setSelected(true);
	    showHeightsBox.addActionListener(
		new ActionListener() {
		    public void actionPerformed( ActionEvent e ) {
			updateView();
		    }
		}
					     );

	    add(showHeightsBox);

	}

	public boolean showHeights() {
	    return showHeightsBox.isSelected();
	}

    }


    class TreePanel extends JPanel {

	public static final int RowHeight = 40;
	public static final int MinHeight = 8*RowHeight;
	public static final int Margin = 20;

	public TreePanel() {
	    super();
	    setBackground(windowColor);
	}

	private Dimension treeSize() {

	    BinaryTreeNode root = model.getRootNode();
	    int height = height(root) * RowHeight;
	    int width = width(root);

	    return new Dimension(width, height);

	}

	public Dimension getPreferredSize() {

	    int height = Math.max(MinHeight, treeSize().height);
	    return new Dimension(treeSize().width + Margin*2, height + Margin*2);

	}

	static final int TextWidth = 260;
	static final int TextHeight = 30;
	static final int TextX = 3;
	static final int TextY = 13;
	static final int TextY2 = 27;

	public void paintComponent(Graphics g) {
	    super.paintComponent(g);

	    BinaryTreeNode root = model.getRootNode();
	    int x = (getSize().width - treeSize().width) / 2;
	    int y = (getSize().height - treeSize().height) / 2;

	    draw(g, root, x, y);

	    //	    draw2(g, root, getSize().width / 2, y);

	    if (heightPanel.showHeights()) {

		g.drawString("Format: word (correct height, your height)", TextX, TextY);
		g.drawRect(0, 0, TextWidth, TextHeight);
		g.setColor(Color.blue);
		g.drawString("If heights don't match, the color is different.", TextX, TextY2);
		g.setColor(Color.black);

	    }
	}

	static final int BlankHeight = 8;
	static final int WordHeight = 4;
	static final int WordSpace = 4;
	static final int LineSpace = 2;

	private int height(BinaryTreeNode t) {
	    if (t == null)
		return -1;
	    else
		return Math.max(height(t.getLeftChild()), height(t.getRightChild())) + 1;
	}

	private String getWord(BinaryTreeNode t) {
	    String word = (String)t.getData();
	    if (heightPanel.showHeights())
		word += " (" + height(t) + "," + t.getHeight() + ")";
	    return word;
	}

	private int wordWidth(Object word) {
	    return getFontMetrics(getFont()).stringWidth((String)word);
	}

	private int width(BinaryTreeNode t) {

	    if (t == null)
		return 0;

	    String word = getWord(t);
	    int half = wordWidth(word) / 2 + WordSpace;

	    int leftWidth = Math.max(half, width(t.getLeftChild()));
	    int rightWidth = Math.max(half, width(t.getRightChild()));

	    return leftWidth + rightWidth;

	}

	private int xroot;

	private int draw(Graphics g, BinaryTreeNode t, int x, int y) {

	    if (t == null)
		return 0;

	    String word = getWord(t);
	    int half = wordWidth(word) / 2;

	    int leftWidth = Math.max(half+WordSpace,
				     draw(g, t.getLeftChild(), x, y+RowHeight));
	    int leftRoot = xroot;

	    int rightWidth = Math.max(half+WordSpace,
				      draw(g, t.getRightChild(), x+leftWidth, y+RowHeight));
	    int rightRoot = xroot;

	    int width = leftWidth + rightWidth;
	    //	    xroot = x + leftWidth;
	    xroot = x + width/2;

	    if (heightPanel.showHeights() && height(t) != t.getHeight())
		g.setColor(Color.blue);

	    g.drawString(word, xroot - half, y+WordHeight);

	    g.setColor(Color.black);

	    if (t.getLeftChild() != null)
		g.drawLine(xroot - LineSpace, y+BlankHeight,
			   leftRoot + LineSpace, y+RowHeight-BlankHeight);

	    if (t.getRightChild() != null)
		g.drawLine(xroot + LineSpace, y+BlankHeight,
			   rightRoot - LineSpace, y+RowHeight-BlankHeight);

	    return width;

	}


	static final int NodeWidth = 7;

	// another way to draw the tree
	public void draw2(Graphics g, BinaryTreeNode t, int x, int y) {

	    if (t == null)
		return;

	    String word = getWord(t);
	    int half = wordWidth(word) / 2;

	    int xSpace = (int)Math.pow(2, height(t)+1) * NodeWidth;

	    int leftRoot = x - xSpace;
	    int rightRoot = x + xSpace;

	    draw2(g, t.getLeftChild(), leftRoot, y+RowHeight);
	    draw2(g, t.getRightChild(), rightRoot, y+RowHeight);

	    if (heightPanel.showHeights() && height(t) != t.getHeight())
		g.setColor(Color.blue);

	    g.drawString(word, x - half, y+WordHeight);

	    g.setColor(Color.black);

	    if (t.getLeftChild() != null)
		g.drawLine(x - LineSpace, y+BlankHeight,
			   leftRoot + LineSpace, y+RowHeight-BlankHeight);

	    if (t.getRightChild() != null)
		g.drawLine(x + LineSpace, y+BlankHeight,
			   rightRoot - LineSpace, y+RowHeight-BlankHeight);

	}

    }

}

