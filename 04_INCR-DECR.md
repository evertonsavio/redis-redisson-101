# Increase / Decrease  
  
### Increase or Decrease by 1  
```
set a 1
incr a
get a
"2"
decr a
get a
"1"
```

### User visit product naming convesion e.g
```
incr prod:a:visit
```
  
### Increasing float values  
```
set a 1.02
OK
get a
"1.02"
incrbyfloat a .3
"1.32"
incrbyfloat a -.3
"1.32"
```

### Increasing x value  
```
set sum 100
incrby sum 20
decrby sum 20
```
