### Access patterns

```
# All keys
keys *

# Key plus something
* keys user*

# Pagination first 10
scan 0
scan ...

# Scan match pattern
scan 0 MATCH user* count 3

```
