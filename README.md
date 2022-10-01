# Openfire


## Setting Up

Run docker image

```
docker run nasqueron/openfire
```

Open local host or machine ip, with port _9090_

> http://172.17.0.2:9090/

Configure basic openfire setup

Go to plungis tab, then avaible plugins

Add Rest API plugins

## Running Postman test

Open postman and import OpenfireCollection.postman_collection.json

Run collection

Can't add users to chatroom, a User can only be "occupant" if it's online and manually joined the chatroom( can be whitelisted)
Can't send indivdual messages, only broadcast to all members
