package QuizWebApp.controller;

import QuizWebApp.domain.*;
import QuizWebApp.domain.request.LoginRequest;
import QuizWebApp.domain.response.*;
import QuizWebApp.security.AuthUserDetail;
import QuizWebApp.security.JwtProvider;
import QuizWebApp.service.OrderService;
import QuizWebApp.service.ResultService;
import QuizWebApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api")
public class ApiController {
    private UserService userService;
    private ResultService resultService;

    private OrderService orderService;

    private AuthenticationManager authenticationManager;

    private JwtProvider jwtProvider;

    @Autowired
    public void setJwtProvider(JwtProvider jwtProvider) {
        this.jwtProvider = jwtProvider;
    }

    @Autowired
    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    public ApiController(UserService userService, ResultService resultService, OrderService orderService) {
        this.userService = userService;
        this.resultService = resultService;
        this.orderService = orderService;
    }

    @GetMapping("/test")
    public Object getAuthUserDetail(){
        return SecurityContextHolder.getContext().getAuthentication();
    }

    @GetMapping("user/{userId}/order/{orderId}")
    public UserOrderResponse getUserOrderById(@PathVariable int userId,
                                              @PathVariable int orderId) {
        List<User> userList = new ArrayList<>();
        userList = userService.getAllUsers();
        userList = userList.stream().filter(user -> user.getId() == userId).collect(Collectors.toList());

        User user = userList.get(0);

        List<Order> orderList = new ArrayList<>();
        orderList = orderService.getAllOrders();
        orderList = orderList.stream().filter(order -> order.getId() == orderId).collect(Collectors.toList());

        Order order = orderList.get(0);

        System.out.println(order);


        List<OrderItemResponse> testList = new ArrayList<>();
        testList.add(new OrderItemResponse("apple", 2));
        testList.add(new OrderItemResponse("banana", 5));
        testList.add(new OrderItemResponse("pineapple", 1));

        UserResponse userResponse = UserResponse.builder()
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .build();

        OrderResponse orderResponse = OrderResponse.builder()
                .orderId(orderId)
                .time(order.getOrderTime())
                .totalPrice(order.getTotalPrice())
                .orderItemResponseList(testList)
                .build();

        return UserOrderResponse.builder()
                .orderResponse(orderResponse)
                .userResponse(userResponse)
                .build();
    }

    @GetMapping("/async/user/{userId}/order/{orderId}")
    public UserOrderResponse getUserOrderByIdAsync(@PathVariable int userId,
                                                   @PathVariable int orderId) {

        CompletableFuture<List<User>> userFuture = userService.getAllUsersAsync();
        CompletableFuture<List<Order>> orderFuture = orderService.getAllOrdersAsync();



        CompletableFuture<UserOrderResponse> responseFuture = CompletableFuture.allOf(
                userFuture,
                orderFuture
        ).thenApply(
                (placeholder) -> {

                    User user = userFuture.join().stream().filter(u -> u.getId() == userId).collect(Collectors.toList()).get(0);
                    Order order = orderFuture.join().stream().filter(o -> o.getId() == orderId).collect(Collectors.toList()).get(0);

                    List<OrderItemResponse> testList = new ArrayList<>();
                    testList.add(new OrderItemResponse("apple", 2));
                    testList.add(new OrderItemResponse("banana", 5));
                    testList.add(new OrderItemResponse("pineapple", 1));

                    UserResponse userResponse = UserResponse.builder()
                            .email(user.getEmail())
                            .firstName(user.getFirstName())
                            .lastName(user.getLastName())
                            .build();

                    OrderResponse orderResponse = OrderResponse.builder()
                            .orderId(orderId)
                            .time(order.getOrderTime())
                            .totalPrice(order.getTotalPrice())
                            .orderItemResponseList(testList)
                            .build();


                    return UserOrderResponse.builder()
                            .orderResponse(orderResponse)
                            .userResponse(userResponse)
                            .build();
                }
        );

        return responseFuture.join();
    }


    @PostMapping("auth/login")
    public LoginResponse login(@RequestBody LoginRequest request){

        Authentication authentication;

        //Try to authenticate the user using the username and password
        try{
            authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
            );
        } catch (AuthenticationException e){
            throw new BadCredentialsException("Provided credential is invalid.");
        }

        //Successfully authenticated user will be stored in the authUserDetail object
        AuthUserDetail authUserDetail = (AuthUserDetail) authentication.getPrincipal(); //getPrincipal() returns the user object

        //A token wil be created using the username/email/userId and permission
        String token = jwtProvider.createToken(authUserDetail);

        //Returns the token as a response to the frontend/postman
        return LoginResponse.builder()
                .message("Successfully Authenticated")
                .token(token)
                .build();

    }

    @GetMapping("user")
    public List<User> getUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("user/{userId}")
    public User getUserById(@PathVariable int userId) {
        List<User> userList = new ArrayList<>();
        userList = userService.getAllUsers();

        userList = userList.stream().filter(user -> user.getId() == userId).collect(Collectors.toList());

        return userList.get(0);
    }

    @GetMapping("permission/{userId}")
    public List<String> getPermissionByUserId(@PathVariable int userId) {
        return userService.getUserPermissionById(userId);
    }

    @GetMapping("quiz/{userId}")
    public List<QuizResult> getQuizzesByUserId(@PathVariable int userId) {
        return resultService.getQuizResultByUserId(userId);
    }

    @PatchMapping("/user/{userId}/status")
    public String updateUserStatus(@PathVariable int userId) {
        userService.reverseUserStatusById(userId);
        return "Status updated";
    }


    @PostMapping("user")
    public String createUser(@RequestBody User newUser) {
        System.out.println(newUser);
        userService.createNewUser(newUser);
        return "User created";
    }

    @DeleteMapping("user/{userId}")
    public String deleteUser(@PathVariable int userId) {
        userService.deleteUserById(userId);
        return "user deleted";
    }

}
