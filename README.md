<h3>This IS a small demonstration about how to design spring boot project to configure all security just in one place.</h3>
<h3> Basic logic here is making http request with RestTemplate in Spring boot to Auth service to check users Permission each time we get request from FRONTEND.</h3>
<img src="https://github.com/JimmyShukurow/YachtRent/blob/main/images/ApiGateway.png" alt="ApiGateway">
<p>This Diagram Shows how system works.</p>
<p>For developers who has Docker I have added docker version of this little demo</p>
<p>Frontend is in Vue3 Quasar and has URL like this <a href="http://localhost:9000">http://localhost:9000</a> </p>
<h3>Basicly if you login with user account you will not be able TO DELETE yachts in yachts page, because only admin hase ADMIN role, and DELETE method is only available for users with ADMIN ROLE.</h3>
