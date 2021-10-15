package Application;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.lang.reflect.Array;
import java.util.EmptyStackException;

class MyStack<T> implements StackFunctions<T> {
    T[] array;
    int top;
    Class<T> newClass;
    int size;

    @SuppressWarnings("unchecked")
    MyStack(Class<T> tush) {
        this.size = 1;
        this.newClass = tush;
        this.array = (T[]) Array.newInstance(tush,this.size);
        this.top=0;
    }

    @Override
    public void push(T value) {
        if(top>=0){
            array = resize();
        }
        this.array[this.top++]=value;
    }

    @SuppressWarnings("unchecked")
    T[]  resize(){
        T[] newArray = (T[]) Array.newInstance(newClass,this.size++);
        System.arraycopy(array,0,newArray,0,array.length);
        return newArray;
    }

    @Override
    public boolean isEmpty() {
        return top==0;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T pop() {
        if(!isEmpty()) {
            T object = array[-1+top--];
            T[] newArray = (T[]) Array.newInstance(newClass,--this.size-1);
            System.arraycopy(array,0,newArray,0,newArray.length);
            array = newArray;
            return object;
        }
        else {
            this.top = 0;
            throw new EmptyStackException();
        }
    }

    @Override
    public void display() {
        for (T e : array){
            System.out.println(e);
        }
    }

}

public class GUI implements KeyListener {

    JFrame frame = new JFrame();
    JButton button1 = new JButton();
    JButton button2 = new JButton();
    JButton button3 = new JButton("EXIT");
    JTextArea textField = new JTextArea();
    MyStack<String> tush;
    MyStack<String> newTush;

    GUI(){
        tush = new MyStack<>(String.class);
        newTush = new MyStack<>(String.class);


        frame.setSize(700,500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setTitle("UNDO-REDO");
        frame.setResizable(false);
        frame.getContentPane().setBackground(Color.DARK_GRAY);
        ImageIcon icon = new ImageIcon("Image/undo.jpg");
        frame.setIconImage(icon.getImage());

        Container container = frame.getContentPane();
        JLabel label = new JLabel("STACK APPLICATION ");
        label.setBounds(40,10,400,40);
        label.setFont(new Font("lato",Font.BOLD,20));
        label.setForeground(Color.orange);
        container.add(label);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(40,50,560,390);
        panel.setBackground(Color.lightGray);

        textField.setBounds(10,10,540,370);
        textField.addKeyListener(this);
        textField.setFont(new Font("Consoles",Font.BOLD,20));
        textField.setBackground(Color.white);
        textField.setCaretColor(Color.BLACK);
        textField.setForeground(Color.black.brighter());
        frame.add(textField);

        frame.add(panel);
        panel.add(textField);


        button1.setBounds(610,50,65,70);
        button1.setFont(new Font("Lato",Font.BOLD,10));
        button1.setLayout(null);
        button1.setBackground(Color.DARK_GRAY);
        button1.setBorderPainted(false);
        ImageIcon buttonIcon1 = new ImageIcon("Image/unddo.png");
        buttonIcon1.getImage().getScaledInstance(50,50,Image.SCALE_DEFAULT);
        button1.setIcon(buttonIcon1);
        container.add(button1);
        button1.addActionListener(this::addAction);

        JLabel  buttonLabel1 = new JLabel("UNDO");
        buttonLabel1.setBounds(620,110,400,40);
        buttonLabel1.setForeground(Color.orange);
        buttonLabel1.setFont(new Font("lato",Font.BOLD,15));
        container.add(buttonLabel1);


        button2.setBounds(610,200,65,70);
        button2.setFont(new Font("Lato",Font.BOLD,10));
        button2.setLayout(null);
        button2.setBackground(Color.DARK_GRAY);
        button2.setBorderPainted(false);
        ImageIcon buttonIcon2 = new ImageIcon("Image/reddo.png");
        buttonIcon2.getImage().getScaledInstance(50,50,Image.SCALE_SMOOTH);
        button2.setIcon(buttonIcon2);
        container.add(button2);
        button2.addActionListener(this::addAction);

        JLabel  buttonLabel2 = new JLabel("REDO");
        buttonLabel2.setBounds(620,260,400,40);
        buttonLabel2.setForeground(Color.orange);
        buttonLabel2.setFont(new Font("lato",Font.BOLD,15));
        container.add(buttonLabel2);


        button3.setFont(new Font("lato",Font.BOLD,12));
        button3.setForeground(Color.white);
        button3.setBounds(610,380,65,40);
        button3.setLayout(null);
        button3.setBackground(Color.red);
        container.add(button3);
        button3.addActionListener(this::addAction);

        frame.setVisible(true);
        container.setVisible(true);
    }

    @Override
    public void keyTyped(KeyEvent event) {
    }
    @Override
    public void keyPressed(KeyEvent event) {

    }
    @Override
    public void keyReleased(KeyEvent event) {
        tush.push(String.valueOf(event.getKeyChar()));
    }
    public void addAction(ActionEvent event){
        if(event.getSource()==button1){
            String temp = tush.pop();
            newTush.push(temp);
            StringBuilder text =  new StringBuilder();
            for (int i =0; i<tush.array.length;i++) {
                text.append(tush.array[i]);
            }
            String setText = text.toString();
            textField.setText(setText);
        }
        if(event.getSource()==button2){
            String jointText = newTush.pop();
            String newText = textField.getText()+jointText;
            textField.setText(newText);
        }
        if (event.getSource()==button3) {
            System.exit(0);
        }
    }
    public static void main(String[] args) {
        new GUI();
    }

}
