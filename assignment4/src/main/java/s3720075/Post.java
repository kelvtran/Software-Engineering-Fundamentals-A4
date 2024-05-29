package s3720075;
import java.io.File;
import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
public class Post {
    private static int postIDCounter = 0; 
    private int postID;
    private String postTitle;
    private String postBody;
    private String [] postTags;
    private String postTypes;
    private String postEmergency;
    private static ArrayList <String> postComments = new ArrayList<>();

    //CONSTRUCTOR
    public Post(String postTitle, String postBody, String[] postTags, String postTypes, String postEmergency) {
        this.postID = postIDCounter++;
        this.postTitle = postTitle;
        this.postBody = postBody;
        this.postTags = postTags;
        this.postTypes = postTypes;
        this.postEmergency = postEmergency;
    }

    public boolean addPost() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("post.txt", true));
            writer.write("Post ID: " + this.postID + "\n");
            writer.write("Title: " + this.postTitle + "\n");
            writer.write("Body: " + this.postBody + "\n");
            writer.write("Tags: ");
            for (String tag : this.postTags) {
                writer.write(tag + " ");
            }
            writer.write("\n");
            writer.write("Type: " + this.postTypes + "\n");
            writer.write("Emergency: " + this.postEmergency + "\n");
            writer.write("Comments: \n");
            writer.write("\n");
            writer.close();
            System.out.println("Post created successfully!");  
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("An error occurred while writing to the file.");
            return false;
        }
    }

    

    //METHODS FOR addPost()
    // Condition 1. The post title should have a minimum of 10 characters and a maximum of 250 characters. 
    // Moreover, the first five characters should not be numbers and special characters. 
    public static boolean isValidTitle(String postTitle) {

        if (postTitle.length() < 10 || postTitle.length() > 250) {
            System.out.println("Title is invalid - should have a minimum of 10 characters and a maximum of 250 characters");
            return false;
        }

        for (int i = 0; i < 5; i++) {
            char ch = postTitle.charAt(i);
            if (!Character.isLetter(ch)) {
                System.out.println("Title is invalid - first five characters should not be numbers and special characters");
                return false;
            }
        }
        System.out.println("Title is valid");
        return true;
    }

    // Condition 2. The post body should have a minimum of 250 characters. 
    public static boolean isValidBody(String postBody) {

        if (postBody.length() < 250) {
            System.out.println("Body is invalid - should have a minimum of 250 characters");
            return false;
        }

        System.out.println("Body is valid");
        return true;
    }

    // Condition 3. Each post should have a minimum of 2 tags and a maximum of 5 tags. 
    // Moreover, tags should have a minimum of 2 characters and a maximum of 10 characters and should not include any upper-case letters.
    public static boolean isValidTags(String[] postTags) {

        if (postTags.length < 2 || postTags.length > 5) {
            System.out.println("Tags are invalid - should have a minimum of 2 tags and a maximum of 5 tags");
            return false;
        }

        for (String tag : postTags) {
            if (tag.length() < 2 || tag.length() > 10) {
                System.out.println("Tags are invalid - tags should have a minimum of 2 characters and a maximum of 10 characters");
                return false;
            }

            for (int i = 0; i < tag.length(); i++) {
                char ch = tag.charAt(i);
                if (Character.isUpperCase(ch)) {
                    System.out.println("Tags are invalid - tags should not include any upper-case letters");
                    return false;
                }
            }
        }

        System.out.println("Tags are valid");
        return true;
    }

    // Condition 4. Regarding difficulty, the post type can be classified into "Very Difficult", "Difficult", or "Easy".
    // "Easy" posts should not have more than 3 tags. 
    //"Very Difficult", "Difficult" posts should have a minimum of 300 characters in their body.
    public static boolean isValidType(String postType, String[] postTags, String postBody) {

        if (!postType.equals("Very Difficult") && !postType.equals("Difficult") && !postType.equals("Easy")) {
            System.out.println("Type is invalid - should be Very Difficult, Difficult, or Easy");
            return false;
        }

        if (postType.equals("Easy") && postTags.length > 3) {
            System.out.println("Type is invalid - Easy posts should not have more than 3 tags");
            return false;
        }

        if ((postType.equals("Very Difficult") || postType.equals("Difficult")) && postBody.length() < 300) {
            System.out.println("Type is invalid - Very Difficult, Difficult posts should have a minimum of 300 characters in their body");
            return false;
        }

        System.out.println("Type is valid");
        return true;
    }

    // Condition 5. Regarding the emergency of a need for an answer, a post can be classified as "Immediately Needed", "Highly Needed", or "Ordinary". 
    //"Easy" posts should not have the "Immediately Needed" and "Highly Needed" statuses. 
    //"Very Difficult" and "Difficult" posts should not have the status of "Ordinary".
    public static boolean isValidEmergency(String postEmergency, String postType) {

        if (!postEmergency.equals("Immediately Needed") && !postEmergency.equals("Highly Needed") && !postEmergency.equals("Ordinary")) {
            System.out.println("Emergency is invalid - should be Immediately Needed, Highly Needed, or Ordinary");
            return false;
        }

        if (postType.equals("Easy") && (postEmergency.equals("Immediately Needed") || postEmergency.equals("Highly Needed"))) {
            System.out.println("Emergency is invalid - Easy posts should not have the Immediately Needed and Highly Needed statuses");
            return false;
        }

        if ((postType.equals("Very Difficult") || postType.equals("Difficult")) && postEmergency.equals("Ordinary")) {
            System.out.println("Emergency is invalid - Very Difficult and Difficult posts should not have the status of Ordinary");
            return false;
        }

        System.out.println("Emergency is valid");
        return true;
    }

    

    //Method for addComment()

    public static boolean addComment(String comment, String postID, String file) {

        ArrayList<String> lines = new ArrayList<>();
        boolean isPostFound = false;
        // scan the file and add the lines to the list
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            System.out.println("Reading the file");
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
                if (line.contains(postID)) {
                    isPostFound = true;
                }
                if (isPostFound && line.startsWith("Comments:")) {
                    lines.add(comment);
                    isPostFound = false; // To stop adding comment after the first match
                    //print array WORKS
                    System.out.println("Printing array");
                    for (String s : lines) {    
                        System.out.println(s);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading the file.");
            e.printStackTrace();
            return false;
        }
        // Write the modified content back to the file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (String line : lines) {
                writer.write(line + "\n");
            }
        
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file.");
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static boolean isValidPostID(String postID, String path) {
        File file = new File(path);
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.contains("Post ID: " + postID)) {
                    return true;
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("Post ID is invalid - post does not exist");
        return false;
    }

    //The comment text should have a minimum of 4 words and a maximum of 10 words. 
    //Moreover, the first character of the first word should be an uppercase letter. 
    //comment should be prefixed with '-'
    public static boolean isValidComment(String comment) {

        String[] words = comment.split(" ");
        if (words.length < 4 || words.length > 10) {
            System.out.println("Comment is invalid - should have a minimum of 4 words and a maximum of 10 words");
            return false;
        }

        char hyphen = words[0].charAt(0);
        if (hyphen != '-') {
            System.out.println("Comment is invalid - comment should be prefixed with '- '");
            return false;
        }

        char ch = words[1].charAt(0);
        if (!Character.isUpperCase(ch)) {
            System.out.println("Comment is invalid - first character of the first word should be an uppercase letter");
            return false;
        }

        System.out.println("Comment is valid");
        return true;
    }

    //"Each" post can have 0 to 5 comments. Posts that are "Easy" or "Ordinary" should have a maximum of 3 comments. 
    public static boolean isValidNumberOfComments(String postID, String file) {

        ArrayList<String> comments = new ArrayList<>();
        boolean isPostFound = false;
        String type = "";

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;

            while ((line = reader.readLine()) != null){
                if (line.contains("Post ID: " + postID)) {
                    isPostFound = true;
                }

                if (isPostFound) {
                    if (line.startsWith("Type: ")) {
                        type = line.substring(6).trim();
                    }
                    if (line.startsWith("- ")) {
                        comments.add(line);
                    }
                    if (line.isEmpty()) {
                        // Finish reading the current post when an empty line is found
                        break;
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading the file.");
            e.printStackTrace();
            return false;
        }

        if (type.equals("Easy") && comments.size() > 3) {
            System.out.println("Number of comments is invalid - Easy posts should have a maximum of 3 comments");
            return false;
        }

        if (comments.size() > 5) {
            System.out.println("Number of comments is invalid - should have a maximum of 5 comments");
            return false;
        }

        System.out.println("Number of comments is valid");
        return true;
    } 


}