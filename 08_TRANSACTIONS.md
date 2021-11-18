### No Transactions   
```
set user:1:balance 1

set user:2:balance 0

get user:1:balance

decr user:1:balance

incr user:1:balance
```

### Transactions
```
set user:1:balance 1
set user:2:balance 0

# Tell Redis to watch this users (Opening transaction)
watch user:1:balance user:2:balance
OK

# Enter in transaction mode
multi
OK
decr user:1:balance
QUEUED
incr user:2:balance
QUEUED

# Execute transaction
exec
(integer) 0
(integer) 1
```
