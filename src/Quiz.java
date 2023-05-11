import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Quiz extends JFrame implements ActionListener {

    private JTextField questionField, correctAmountField, percentage;
    private JTextArea questionArea;
    private Timer timer;
    private JButton optionA, optionB, optionC, optionD;
    private JLabel answerA, answerB, answerC, answerD;
    private JLabel time, secondsLeft;
    private String[] questions = {
            "Which company created Java?",
            "Which year was Java created?",
            "What was Java originally called?",
            "What is Zerkaa's alter ego?"
    };
    private String[][] options = {
            {"Sun Microsystems", "Starbucks", "Microsoft", "Alphabet"},
            {"1989", "1996", "1972", "1492"},
            {"Apple", "Latte", "Oak", "Koffing"},
            {"Adam Abunda", "Dwayne False", "Moses Naan", "Tommy Tits"}
    };
    private char[] answers = {'A','B','C','D'};
    private char guess;
    private char correctAnswer;
    private int index;
    private int correctAmount;
    private int totalQuestions;
    private int result;
    private int seconds;

    public Quiz(){
        totalQuestions = questions.length;
        seconds = 10;
        correctAmount = 0;

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(650,650);
        this.getContentPane().setBackground(new Color(50,50,50));
        this.setLayout(null);
        this.setLocationRelativeTo(null);

        questionField = new JTextField();
        questionField.setBounds(0,0,650,50);
        questionField.setBackground(new Color(25,25,25));
        questionField.setForeground(new Color(25,255,0));
        questionField.setFont(new Font("Ink Free", Font.BOLD, 30));
        questionField.setBorder(BorderFactory.createBevelBorder(1));
        questionField.setHorizontalAlignment(JTextField.CENTER);
        questionField.setEditable(false);

        questionArea = new JTextArea();
        questionArea.setBounds(0,50,650,50);
        questionArea.setBackground(new Color(25,25,25));
        questionArea.setForeground(new Color(25,255,0));
        questionArea.setFont(new Font("MV Boli", Font.BOLD, 25));
        questionArea.setBorder(BorderFactory.createBevelBorder(1));
        questionArea.setLineWrap(true);
        questionArea.setWrapStyleWord(true);
        questionArea.setEditable(false);

        optionA = new JButton("A");
        optionA.setBounds(0,100,100,100);
        optionA.setFont(new Font("MV Boli", Font.BOLD, 35));
        optionA.setFocusable(false);
        optionA.addActionListener(this);

        optionB = new JButton("B");
        optionB.setBounds(0,200,100,100);
        optionB.setFont(new Font("MV Boli", Font.BOLD, 35));
        optionB.setFocusable(false);
        optionB.addActionListener(this);

        optionC = new JButton("C");
        optionC.setBounds(0,300,100,100);
        optionC.setFont(new Font("MV Boli", Font.BOLD, 35));
        optionC.setFocusable(false);
        optionC.addActionListener(this);

        optionD = new JButton("D");
        optionD.setBounds(0,400,100,100);
        optionD.setFont(new Font("MV Boli", Font.BOLD, 35));
        optionD.setFocusable(false);
        optionD.addActionListener(this);

        answerA = new JLabel();
        answerA.setBounds(125,100,500,100);
        answerA.setBackground(new Color(50,50,50));
        answerA.setForeground(new Color(25,255,0));
        answerA.setFont(new Font("MV Boli", Font.PLAIN, 35));

        answerB = new JLabel();
        answerB.setBounds(125,200,500,100);
        answerB.setBackground(new Color(50,50,50));
        answerB.setForeground(new Color(25,255,0));
        answerB.setFont(new Font("MV Boli", Font.PLAIN, 35));

        answerC = new JLabel();
        answerC.setBounds(125,300,500,100);
        answerC.setBackground(new Color(50,50,50));
        answerC.setForeground(new Color(25,255,0));
        answerC.setFont(new Font("MV Boli", Font.PLAIN, 35));

        answerD = new JLabel();
        answerD.setBounds(125,400,500,100);
        answerD.setBackground(new Color(50,50,50));
        answerD.setForeground(new Color(25,255,0));
        answerD.setFont(new Font("MV Boli", Font.PLAIN, 35));

        secondsLeft = new JLabel(String.valueOf(seconds));
        secondsLeft.setBounds(535,510,100,100);
        secondsLeft.setBackground(new Color(25,25,25));
        secondsLeft.setForeground(new Color(255,0,0));
        secondsLeft.setFont(new Font("Ink Free", Font.BOLD, 60));
        secondsLeft.setBorder(BorderFactory.createBevelBorder(1));
        secondsLeft.setOpaque(true);
        secondsLeft.setHorizontalAlignment(JTextField.CENTER);

        time = new JLabel("Timer");
        time.setBounds(535,475,100,25);
        time.setBackground(new Color(50,50,50));
        time.setForeground(new Color(255,0,0));
        time.setFont(new Font("MV Boli", Font.PLAIN, 20));
        time.setHorizontalAlignment(JTextField.CENTER);

        correctAmountField = new JTextField();
        correctAmountField.setBounds(225,225,200,100);
        correctAmountField.setBackground(new Color(25,25,25));
        correctAmountField.setForeground(new Color(25,255,0));
        correctAmountField.setFont(new Font("Ink Free", Font.BOLD, 50));
        correctAmountField.setBorder(BorderFactory.createBevelBorder(1));
        correctAmountField.setHorizontalAlignment(JTextField.CENTER);
        correctAmountField.setEditable(false);

        percentage = new JTextField();
        percentage.setBounds(225,325,200,100);
        percentage.setBackground(new Color(25,25,25));
        percentage.setForeground(new Color(25,255,0));
        percentage.setFont(new Font("Ink Free", Font.BOLD, 50));
        percentage.setBorder(BorderFactory.createBevelBorder(1));
        percentage.setHorizontalAlignment(JTextField.CENTER);
        percentage.setEditable(false);

        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                seconds--;
                secondsLeft.setText(String.valueOf(seconds));

                if (seconds == 0){
                    displayAnswer();
                }
            }
        });

        this.add(questionField);
        this.add(questionArea);
        this.add(optionA);
        this.add(optionB);
        this.add(optionC);
        this.add(optionD);
        this.add(answerA);
        this.add(answerB);
        this.add(answerC);
        this.add(answerD);
        this.add(secondsLeft);
        this.add(time);
        this.setVisible(true);

        nextQuestion();
    }

    public void nextQuestion(){
        if (index >= totalQuestions){
            results();
        } else{
            questionField.setText("Question: " + (index+1));
            questionArea.setText(questions[index]);
            answerA.setText(options[index][0]);
            answerB.setText(options[index][1]);
            answerC.setText(options[index][2]);
            answerD.setText(options[index][3]);
            timer.start();
        }
    }

    public void displayAnswer(){
        timer.stop();
        optionA.setEnabled(false);
        optionB.setEnabled(false);
        optionC.setEnabled(false);
        optionD.setEnabled(false);

        if (answers[index] != 'A') {
            answerA.setForeground(Color.red);
        }
        if (answers[index] != 'B') {
            answerB.setForeground(Color.red);
        }
        if (answers[index] != 'C') {
            answerC.setForeground(Color.red);
        }
        if (answers[index] != 'D') {
            answerD.setForeground(Color.red);
        }

        Timer pause = new Timer(2000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                answerA.setForeground(new Color(25,255,0));
                answerB.setForeground(new Color(25,255,0));
                answerC.setForeground(new Color(25,255,0));
                answerD.setForeground(new Color(25,255,0));

                guess = ' ';
                seconds = 10;
                secondsLeft.setText(String.valueOf(seconds));

                optionA.setEnabled(true);
                optionB.setEnabled(true);
                optionC.setEnabled(true);
                optionD.setEnabled(true);

                index++;
                nextQuestion();
            }
        });

        pause.setRepeats(false); //Make the timer execute once
        pause.start();
    }

    public void results(){
        optionA.setEnabled(false);
        optionB.setEnabled(false);
        optionC.setEnabled(false);
        optionD.setEnabled(false);

        result = (int) (correctAmount/(double) totalQuestions * 100);
        questionField.setText("Results");
        questionArea.setText("");
        answerA.setText("");
        answerB.setText("");
        answerC.setText("");
        answerD.setText("");
        correctAmountField.setText(correctAmount + "/" + totalQuestions);
        percentage.setText(result + "%");

        this.add(percentage);
        this.add(correctAmountField);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        optionA.setEnabled(false);
        optionB.setEnabled(false);
        optionC.setEnabled(false);
        optionD.setEnabled(false);

        if (e.getSource() == optionA) {
            guess = 'A';

            if (guess == answers[index]) {
                correctAmount++;
            }
        } else if (e.getSource() == optionB) {
            guess = 'B';

            if (guess == answers[index]){
                correctAmount++;
            }
        } else if (e.getSource() == optionC) {
            guess = 'C';

            if (guess == answers[index]){
                correctAmount++;
            }
        } else if (e.getSource() == optionD){
            guess = 'D';

            if (guess == answers[index]){
                correctAmount++;
            }
        }

        displayAnswer();
    }
}
