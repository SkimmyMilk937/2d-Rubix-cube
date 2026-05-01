import javax.swing.SwingUtilities;

public class Launcher
{
    public static void main(String[] args) 
    {
        //Only Run in One Thread - Ensure Thread Safe
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run()
            {
                RubikGUI main = new RubikGUI();
                main.show();
            }
        });


    }
}