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

### Redis remove set  
```
sismember users 5
srem users 5
(integer) 1

srem users 5
(integer) 0
```

### Random remove
```
spop users
"3"
```
   
### Intersection & Union  
```   
sadd skill:java 1 2 3 4
sadd skill:javaScript 2 3 4
sadd skill:aws 4 5 6

# Intersection  
sinter skill:java skill:javaScript skill:aws
"4"

# sunion skill:java skill:javaScript
"1"
"2"
"3"
"4"

# sdiff skill:java skill:aws

# sinterstore java-js skill:java skill:javaScript
keys *
"java-js"
"skill:java"
"skill:javaScript"
"skill:aws"

```   
