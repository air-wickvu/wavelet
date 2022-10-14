import java.io.IOException;
import java.net.URI;
import java.util.*; 

class Handler implements URLHandler {
    // The one bit of state on the server: a number that will be manipulated by
    // various requests.
    ArrayList<String> items = new ArrayList <String>(); 

    public String handleRequest(URI url) {
        System.out.println("Path: " + url.getPath());

        if(url.getPath().contains("/add")){ 
            String[] parameters = url.getQuery().split("=");
            items.add(0,parameters[1]);
            return parameters[1];
        } else if(url.getPath().contains("/search")){
            
            String[] parameters = url.getQuery().split("=");
            ArrayList<String> matchedWords = new ArrayList<String>(); 

            for(int i= 0; i<items.size();i++){
                if(items.get(i).contains(parameters[1])){
                    matchedWords.add(0,items.get(i));
                }
            } 
            return matchedWords.toString();    
        }
    return "404 Not Found!";
    }   
}


class NumberServer {
    public static void main(String[] args) throws IOException {
        if(args.length == 0){
            System.out.println("Missing port number! Try any number between 1024 to 49151");
            return;
        }

        int port = Integer.parseInt(args[0]);

        Server.start(port, new Handler());
    }
}