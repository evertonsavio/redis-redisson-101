# Hash  
---
  
### Set a hash  
```
hset user:1 name sam age 10 city atlanta
(integer) 3

keys *
"user:1"

type user:1
hash
```

### Select a value  
```
hget user:1 name
"sam"

hset user:1 age
"10"

hgetall user:1
"name"
"sam"
"age"
"10"
"city"
"atlanta"
```

### Expiring hash
```
expire user:1 10
```

### Check if exists  
```
hexists user:1 status
(integer) 0

hexists user:1 status
(integer) 1
```
