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

## Sorted Set
```
flushdb

zadd products 0 books
zadd products 0 iphone 0 tv
zcard products

zincrby products 1 books
"1"
zincrby products 1 iphone
"1"
zincrby products 1 iphone
"2"
zincrby products 1 tv
"1"

zrange products 0 -1
"books"
"tv"
"iphone"

zincrby products 1 iphone
"3"
zincrby products 1 books
"2"

zrange products 0 -1
"tv"
"books"
"iphone"

zrange products 0 -1 withscores
"tv"
"1"
"books"
"2"
"iphone"
"3"

zrange products -1 -1
"iphone"
zrange products 0 0 rev
"iphone"
zrange products 0 0 rev withscores
"iphone"
"3"
zrange products 0 1 rev withscores

zrank products books
(integer) 1
zrank products iphone
(integer) 2

zscore products iphone
"3"

zpopmax products
"iphone"
"3"

zcard products
```
