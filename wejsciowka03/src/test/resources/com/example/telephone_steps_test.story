Given a telephone
When add one element
Then telephone size should return 1

Given a telephone
When add one element
When add one element
Then telephone size should return 2

Given a setupped telephone
When remove one element
Then telephone size should return 3

Given a telephone
When add one element
When add one element
When add one element
When add one element
When remove one element
Then telephone size should return 3
