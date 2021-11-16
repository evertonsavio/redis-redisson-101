## SET  
  
* Unordered collections of UNIQUE items (string)  
* Use cases: maintain currently logged in users, blacklist IP address / users 
  
### Redis set  
```  
sadd users 1 2 3  
  
scard users  
(integer) 3  
  
smembers users  
"1"  
"2"  
"3"  
```  
