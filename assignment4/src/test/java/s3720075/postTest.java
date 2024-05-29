package s3720075;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class postTest {

    //TEST CASE 1
    @Test
    public void validateTitleLengthFail(){
        assertEquals(false, Post.isValidTitle("Lorem Ips"));
    }
    @Test
    public void validateTitleLengthPass(){
        assertEquals(true, Post.isValidTitle("Lorem Ipsu"));
    }

    //TEST CASE 2
    @Test
    public void validateTitleFormatFail(){
        assertEquals(false, Post.isValidTitle("@L0r3m Ipsum"));
    }
    @Test
    public void validateTitleFormatPass(){
        assertEquals(true, Post.isValidTitle("Lorem Ipsum"));
    }

    //TEST CASE 3
    @Test
    public void validateBodyLengthFail(){
        assertEquals(false, Post.isValidBody("This is the body"));
    }

    @Test
    public void validateBodyLengthPass(){
        String body = "Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo. Nemo enim ipsam voluptatem quia voluptas sit aspernatur aut odit aut fugit, sed quia consequuntur magni dolores eos qui ratione voluptatem sequi nesciunt. Neque porro quisquam est, qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit, sed quia non numquam eius modi tempora incidunt ut labore et dolore magnam aliquam quaerat voluptatem. Ut enim ad minima veniam, quis nostrum exercitationem ullam corporis suscipit laboriosam, nisi ut aliquid ex ea commodi consequatur? Quis autem vel eum iure reprehenderit qui in ea voluptate velit esse quam nihil molestiae consequatur, vel illum qui dolorem eum fugiat quo voluptas nulla pariatur?";  
        assertEquals(true, Post.isValidBody(body));
    }

    //TEST CASE 4
    @Test
    public void validateTagNumFail(){
        String[] tags = {"tag1"};
        assertEquals(false, Post.isValidTags(tags));
    }

    @Test
    public void validateTagNumFail1(){
        String[] tags = {"tag1", "tag2", "tag3", "tag4", "tag5", "tag6"};
        assertEquals(false, Post.isValidTags(tags));
    }

    //TEST CASE 5
    @Test
    public void validateTagFormatPass(){
        String[] tags = {"tag1", "tag2"};
        assertEquals(true, Post.isValidTags(tags));
    }

    @Test
    public void validateTagFormatFail(){
        String[] tags = {"Tag1", "Tag2"};
        assertEquals(false, Post.isValidTags(tags));
    }

    //TEST CASE 6
    @Test
    public void validateCorrectNumTagForEasyFail(){
        String[] tags = {"tag1", "tag2", "tag3", "tag4"};
        assertEquals(false, Post.isValidType("Easy", tags, "This is the body"));
    }

    @Test
    public void validateCorrectNumTagForEasyPass(){
        String[] tags = {"tag1", "tag2", "tag3"};
        assertEquals(true, Post.isValidType("Easy", tags, "This is the body"));
    }

    //COMMENTS
    //TEST CASE 1
    @Test
    public void validateCommentLengthFail(){
        assertEquals(false, Post.isValidComment("My comment"));
    }

    @Test 
    public void validateCommentLengthPass(){
        assertEquals(true, Post.isValidComment("My comment is long enough"));
    }

    //TEST CASE 2
    @Test 
    public void validateCommentFormatPass(){
        assertEquals(true, Post.isValidComment("- This is an acceptable comment"));
    }

    public void validateCommentFormatFail(){
        assertEquals(false, Post.isValidComment("This is an unacceptable comment"));
    }

    //TEST CASE 3
    
    @Test
    public void validateCommentFormatFail2(){
        assertEquals(false, Post.isValidComment("- this is an unacceptable comment"));
    }

    @Test
    public void validateCommentFormatPass2(){
        assertEquals(true, Post.isValidComment("- This is an acceptable comment"));
    }

    //TEST CASE 4   TEST UNDER
    @Test
    public void validateCommentPostIDPass(){
        String file = "test.txt";
        assertEquals(true, Post.isValidPostID("3", file));
    }

    @Test 
    public void validateCommentPostIDFail(){
        String file = "test.txt";
        assertEquals(false, Post.isValidPostID("10", file));
    }

    //TEST CASE 5
    @Test
    public void validateNumberOfCommentsEasyPass(){
        String file = "test.txt";
        assertEquals(true, Post.isValidNumberOfComments("0", file));
    }

    @Test
    public void validateNumberOfCommentsEasyFail(){
        String file = "test.txt";
        assertEquals(false, Post.isValidNumberOfComments("1", file));
    }

    //TEST CASE 6
    @Test
    public void validateNumberOfCommentsPass(){
        String file = "test.txt";
        assertEquals(true, Post.isValidNumberOfComments("2", file));
    }

    @Test
    public void validateNumberOfCommentsFail(){
        String file = "test.txt";
        assertEquals(false, Post.isValidNumberOfComments("3", file));
    }
    
}
