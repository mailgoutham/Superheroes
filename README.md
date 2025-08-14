# Changes made by Goutham
1) Moved static Character to local variable inside the method. (Make it thread safe)
2) Removed Object mapper (Spring Rest by default deserializes the objec to Json)
3) Added Global Exception Handler for status codes and logging
4) Added Custom exception handler (InvalidBattleException, CharacterNotFoundException, ConflictCharacterTypeException )
5) Added BattleService to make BattleContoller lightweight
6) Added WinnerLogicService to segregate the winning logic to its own class and test
7) Added AWS or Provider exception handler to capture any network or thirdparty failure
8) Both unt tests and integration test has been enhanced to cover all acceptance criteria


# Build Circle Superheroes tech test

Superheroes and supervillains are always battling it out, but how do we know who wins? This repo contains an API that gives us that answer. The API contains a `/battle` endpoint which takes a hero and a villain and returns the character that wins. 

The characters and their stats are stored in a json file stored in AWS S3 - https://s3.eu-west-2.amazonaws.com/build-circle/characters.json

Our `BattleController` pulls the json file from S3 and works out the winner by comparing the scores from the json.

## Battle endpoint

Have a look at the `/battle` endpoint. How would you make this better?

How would you improve the tests in `com.buildcircle.superheroes/BattleTests.java`?

## Weaknesses

Some superheroes are particularly weak against certain supervillains. If a hero has a villain specifed in their `weakness` field then they have 1 point knocked off their score when fighting that villain. This can affect the outcome of the battle.

Change the `/battle` endpoint to support this functionality.

## Validation

Superheroes can obviously only fight supervillains and vice versa. Add some validation to make sure the opposite does not happen.


## Acceptance tests

Run the application.

1. Should return Joker - http://localhost:5000/battle?hero=Batman&villain=Joker
2. Should return Superman - http://localhost:5000/battle?hero=Superman&villain=Lex%20Luthor

