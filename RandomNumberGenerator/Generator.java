import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;


/**
 * Generates a table in HTML with user data in one column and a groupings of
 * random numbers in the other. User specifies number of numbers, digits, and
 * participants in the survey. Launches default browser to display HTML table.
 *
 * @author Sandeep Raghunandhan
 * @version May 20, 2015
 * @author Period: 6
 * @author Assignment: RandomNumberGenerator
 * @source Online HTML tutorials and StackOverFlow
 */
public class Generator
{
    private Random rand;

    private String userInfo;

    private String directory;

    private String fileLocation;


    public Generator()
    {
        rand = new Random();
        userInfo = "Name: <br> Age: <br> Gender: "
            + " <br>   Hours of Sleep Last Night:  <br> "
            + " Average Hours of Sleep in Past week:  <br> "
            + "  Number of Numbers Remembered:";
        directory = System.getProperty( "user.dir" );

    }


    public String generateNumWithDigits( int numDigits, int numOfNums )
    {
        StringBuffer num = new StringBuffer();
        for ( int i = 0; i < numOfNums; i++ )
        {
            for ( int j = 0; j < numDigits; j++ )
            {
                num.append( rand.nextInt( 10 ) + "" );
            }
            num.append( " " );
        }
        return num.toString();
    }


    public void createHtmlSurveyFile(
        int numDigits,
        int numOfNums,
        int numParticipants ) throws IOException
    {
        PrintWriter out = new PrintWriter( new BufferedWriter( new FileWriter( "Survey.html" ) ) );
        out.print( "<html>" );
        out.print( "<head>" );
        out.print( "<title>Hours of Sleep v. Short Term Memory Survey</title>" );
        out.print( "</head>" );
        out.print( "<body>" );
        out.print( "<table border cellpadding=5>" );
        out.print( "<tr><th>Info</th><th>Random Number</th></tr>" );
        for ( int i = 0; i < numParticipants; i++ )
        {
            out.print( "<tr><td width = 50%>" + userInfo
                + "</td><td width = 50%>"
                + this.generateNumWithDigits( numDigits, numOfNums )
                + "</td></tr>" );
        }
        out.print( "</table>" );
        out.print( "</body>" );
        out.print( "</html>" );
        out.close();
    }


    public void prompt() throws IOException
    {
        Scanner scan = new Scanner( System.in );
        try
        {
            System.out.println( "Welcome to the Stats Sleep Study Survey Generator!" );
            System.out.println();
            System.out.print( "Number of Digits per group: " );
            int numDigits = scan.nextInt();
            System.out.print( "Number of Numbers: " );
            int numOfNums = scan.nextInt();
            System.out.print( "Number of Participants: " );
            int numParticipants = scan.nextInt();
            this.createHtmlSurveyFile( numDigits, numOfNums, numParticipants );
            System.out.println();
            System.out.println( "Launching HTML doc on Default Browser..." );
            fileLocation = "file:///" + directory + "/" + "Survey.html";
            java.awt.Desktop.getDesktop()
                .browse( java.net.URI.create( fileLocation ) );

        }
        catch ( InputMismatchException i )
        {
            System.out.println( "Please try again!" );
            prompt();
        }
        scan.close();
    }


    public static void main( String[] args ) throws IOException
    {
        Generator gen = new Generator();
        gen.prompt();
    }
}
