# Expiring Keys  

```
keys *
set a b ex 10

get a
"b"

ttl a
2

after 10 seconds
get a
(nil)
```

### Extend 0 seconds  
```
expire a 60
```

### Unix time expiring  
```
set a b exat 1624737928
OK

ttl a
198
```

### Miliseconds expire  
```
set b c px 3000
```

### Change values and stting new expiring  
```
set a b ex 60
OK
set a c keepttl
```

### Check if exists  
```
exists a
(integer 1)
```
