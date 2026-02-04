import Models.Post;
import Models.User;
import Service.*;

import java.util.List;
import java.util.Scanner;

public class ConsoleUI {

    private final Scanner sc = new Scanner(System.in);

    private final UserService userService = new UserService();
    private final PostService postService = new PostService();
    private final LikeService likeService = new LikeService();
    private final CommentService commentService = new CommentService();
    private final ConnectionService connectionService = new ConnectionService();

    private User loggedInUser = null;

    private boolean isRunning = true;


    /* APP LOOP Working Mechanishm */

    public void start() {
        while (isRunning) {
            if (loggedInUser == null) {
                authMenu();
            } else {
                mainMenu();
            }
        }
    }

    /* ===================== Authentication Part ===================== */

    private void authMenu() {
        System.out.println("\n---********* RevConnect *********---");

        System.out.println("1. Register");
        System.out.println("2. Login");
        System.out.println("3. Exit");
        System.out.println("Enter a Number:");

        int choice = Integer.parseInt(sc.nextLine());

        switch (choice) {
            case 1 -> register();
            case 2 -> login();
            case 3 -> exitApp();
            default -> System.out.println("Invalid Credentials.....");
        }
    }

    private void register() {
        System.out.print("Email: ");
        String email = sc.nextLine();

        System.out.print("Password: ");
        String password = sc.nextLine();

        System.out.print("=====  Choose Account Type:  =====\n");
        System.out.println("PERSONAL -- For Normal Users ");
        System.out.println("CREATOR  --For Content Creators ");
        System.out.println("BUSINESS --For Companies or Brands ");
        System.out.println("Enter a Type of Account:");
        String type = sc.nextLine();

        boolean success = userService.register(email, password, type);
        System.out.println(success ? " Registation Done Successfully " : "Registration failed");
    }

    private void login() {
        System.out.print("Enter Registered Email: ");
        String email = sc.nextLine();

        System.out.print("Password: ");
        String password = sc.nextLine();

        loggedInUser = userService.login(email, password);

        if (loggedInUser != null) {
            System.out.println("Logged in");
            System.out.println("Your User ID: " + loggedInUser.getId());
        } else {
            System.out.println("Invalid credentials");
        }
    }



    /* ===================== MAIN MENU ===================== */

    private void mainMenu() {
        System.out.println("\n--- ###### Main Menu #######---");
        System.out.println("1. Create Post");
        System.out.println("2. View My Posts");
        System.out.println("3. Like Post");
        System.out.println("4. Unlike Post");
        System.out.println("5. Delete Post");
        System.out.println("6. Add Comment");
        System.out.println("7. View Comments");
        System.out.println("8. Delete Comment");
        System.out.println("9. Send Connection Request");
        System.out.println("10. View Pending Requests");
        System.out.println("11. Accept Request");
        System.out.println("12. Reject Request");
        System.out.println("13. View Connections");
        System.out.println("14. Logout");
        System.out.println(" Please Enter a Number in between 1 to 14:");

        int choice = Integer.parseInt(sc.nextLine());

        switch (choice) {
            case 1 -> createPost();
            case 2 -> viewMyPosts();
            case 3 -> likePost();
            case 4 -> unlikePost();
            case 5 -> deletePost();
            case 6 -> addComment();
            case 7 -> viewComments();
            case 8 -> deleteComment();
            case 9 -> sendRequest();
            case 10 -> viewPendingRequests();
            case 11 -> acceptRequest();
            case 12 -> rejectRequest();
            case 13 -> viewConnections();
            case 14 -> logout();
            default -> System.out.println("Invalid choice");
        }
    }

    /* ===================== POSTS ===================== */

    private void createPost() {
        System.out.print("Content: ");
        String content = sc.nextLine();
        postService.createPost(loggedInUser.getId(), content);
        System.out.println("Post created");
    }

    private void viewMyPosts() {
        List<Post> posts = postService.getMyPosts(loggedInUser.getId());

        if (posts.isEmpty()) {
            System.out.println("No posts found");
            return;
        }

        for (Post p : posts) {
            System.out.println("----------------");
            System.out.println("Post ID: " + p.getId());
            System.out.println(p.getContent());
        }
    }

    private void deletePost() {
        viewMyPosts();
        System.out.print("Enter Post ID to delete: ");
        long postId = Long.parseLong(sc.nextLine());

        boolean success = postService.deletePost(postId, loggedInUser.getId());
        System.out.println(success ? "Post deleted" : "Cannot delete post");
    }

    /* ========== Likes Section======= */

    private void likePost() {
        viewMyPosts();
        System.out.print("Enter Post ID to like: ");
        long postId = Long.parseLong(sc.nextLine());
        likeService.like(loggedInUser.getId(), postId);
    }

    private void unlikePost() {
        viewMyPosts();
        System.out.print("Enter Post ID to unlike: ");
        long postId = Long.parseLong(sc.nextLine());
        likeService.unlike(loggedInUser.getId(), postId);
    }

    /* ============ Comments Section =========== */

    private void addComment() {
        viewMyPosts();
        System.out.print("Post ID: ");
        long postId = Long.parseLong(sc.nextLine());

        System.out.print("Comment: ");
        String text = sc.nextLine();

        if (text.isBlank()) {
            System.out.println("Comment cannot be empty");
            return;
        }

        commentService.addComment(loggedInUser.getId(), postId, text);
    }

    private void viewComments() {
        System.out.print("Post ID: ");
        long postId = Long.parseLong(sc.nextLine());
        commentService.viewComments(postId);
    }

    private void deleteComment() {
        System.out.print("Comment ID: ");
        long commentId = Long.parseLong(sc.nextLine());
        commentService.deleteComment(commentId, loggedInUser.getId());
    }

    /* ===================== Connections ===================== */

    private void sendRequest() {
        System.out.print("Enter User ID to connect: ");
        long receiverId = Long.parseLong(sc.nextLine());
        connectionService.sendRequest(loggedInUser.getId(), receiverId);
    }

    private void viewPendingRequests() {
        connectionService.viewPending(loggedInUser.getId());
    }

    private void acceptRequest() {
        System.out.print("Enter Request ID to accept: ");
        long requestId = Long.parseLong(sc.nextLine());
        connectionService.accept(requestId);
    }

    private void rejectRequest() {
        System.out.print("Enter Request ID to reject: ");
        long requestId = Long.parseLong(sc.nextLine());
        connectionService.reject(requestId);
    }

    private void viewConnections() {
        connectionService.viewConnections(loggedInUser.getId());
    }

    /* ===================== LOGOUT ===================== */

    private void logout() {
        loggedInUser = null;
        System.out.println("Logged out successfully");
    }

    private void exitApp() {
        isRunning = false;
        sc.close();
        System.out.println("You are exiting RevConnectAPP. Thank you for using the application.");

    }
}
