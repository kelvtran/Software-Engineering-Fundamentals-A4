package s3720075;
import java.util.Scanner;



public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
        System.out.println("So you want to write something jack..? Or you just gonna stand there?");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Post or Comment for today? [P or C]");
        String postOrComment = scanner.nextLine();
        while (!postOrComment.equalsIgnoreCase("P") && !postOrComment.equalsIgnoreCase("C")) {
            System.out.println("Invalid input! Post or Comment for today? [P or C]");
            postOrComment = scanner.nextLine();
        }

        //ADD POST
        if (postOrComment.equalsIgnoreCase("P")) {
            System.out.println("What's the title of your post? Gotta have 10 to 250 characters.. and the first 5 letters better not be numbers or special characters!");

            //TITLE CHECK
            String title = scanner.nextLine();
            while (!Post.isValidTitle(title)) {
                title = scanner.nextLine();
            }
        
            System.out.println("Now, what's the body of your post? Gotta have 250 characters at least!");

            //BODY CHECK
            String body = scanner.nextLine();
            while (!Post.isValidBody(body)) {
                body = scanner.nextLine();
            }

            System.out.println("Now, what are the tags for your post? Gotta have 2 to 5 tags, each tag should have 2 to 10 characters and no upper-case letters!");
            System.out.println("Enter the tags separated by a space");

            //TAGS CHECK
            String tags = scanner.nextLine();
            String[] tagsArray = tags.split(" ");
            while (!Post.isValidTags(tagsArray)) {
                tags = scanner.nextLine();
                tagsArray = tags.split(" ");
            }

            System.out.println("What type of post is this? [Very Difficult, Difficult, Easy]");
            System.out.println("Just note: Easy posts should not have more than 3 tags and Very Difficult and Difficult posts should have a minimum of 300 characters in their body");

            //TYPE CHECK
            String type = scanner.nextLine();
            while (!Post.isValidType(type, tagsArray, body)) {
                type = scanner.nextLine();
            }

            System.out.println("Almost there! How urgent is this post? [Immediately Needed, Highly Needed, Ordinary]");
            System.out.println("Just note: Easy posts shouldn't have 'Immediately Needed' or 'Highly Needed' urgency");
            System.out.println("And Very Difficult and Difficult posts should not have 'Ordinary' urgency");

            //EMERGENCY CHECK
            String emergency = scanner.nextLine();
            while (!Post.isValidEmergency(emergency, type)) {
                emergency = scanner.nextLine();
                }
            
            //WRITING TO FILE
            System.out.println("Writing to text file.."); 
            Post post = new Post(title, body, tagsArray, type, emergency);
            post.addPost();  
            }
        
        //ADD COMMENT
        else {
            System.out.println("What's the postID of the post you want to comment on?");
            String postID = scanner.nextLine();
            String file = "post.txt";

            //POSTID CHECK
            while (!Post.isValidPostID(postID, file)) {
                postID = scanner.nextLine();
                System.out.println(postID);
            }

            System.out.println("What's your comment? Gotta have 4 to 10 words and the first letter should be capitalised!");
            System.out.println("You also gottat prefix the comment with a '- '");

            //COMMENT CHECK
            String comment = scanner.nextLine();
            while (!Post.isValidComment(comment)) {
                comment = scanner.nextLine();
            }
            while (!Post.isValidNumberOfComments(postID, "post.txt")) { //FIX
                comment = scanner.nextLine();
            }
            System.out.println(comment);

            //WRITING TO FILE
            System.out.println("Writing to text file..");
            Post.addComment(comment, postID, "post.txt");
            System.out.println("Comment added successfully!");
            

        }
       
        
    }
}