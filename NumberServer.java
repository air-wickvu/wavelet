import java.io.IOException;
import java.net.URI;

class Handler implements URLHandler {
    // The one bit of state on the server: a number that will be manipulated by
    // various requests.
    int num = 0;

    public String handleRequest(URI url) {                  
        if (url.getPath().equals("/")) {                            //Get url path and check if it has a "/""
            return String.format("Number: %d", num);                //Return a message "Number: "
        } else if (url.getPath().equals("/increment")) {            //Get url path and check if it has "increment"
            num += 1;                                               //increment value 
            return String.format("Number incremented!");            //Output a mesage 
        } else {
            System.out.println("Path: " + url.getPath());           //Prints out path
            if (url.getPath().contains("/add")) {                   // check if path contains "/add"
                String[] parameters = url.getQuery().split("=");    // Get Query? then split with "="??
                if (parameters[0].equals("count")) {
                    num += Integer.parseInt(parameters[1]);
                    return String.format("Number increased by %s! It's now %d", parameters[1], num);
                }
            }
            return "404 Not Found!";
        }
    }
}

class NumberServer {
    public static void main(String[] args) throws IOException {
        if(args.length == 0){       //What is the arg? is it the url link? if the length = 0 then no port number
            System.out.println("Missing port number! Try any number between 1024 to 49151");
            return;
        }

        int port = Integer.parseInt(args[0]);

        Server.start(port, new Handler());
    }
}