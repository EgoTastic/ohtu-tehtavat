Feature: A new user account can be created if a proper unused username and password are given

    Scenario: creation is successful with valid username and password
        Given command new is selected
        When username "matti" and password "ittam6matti" are entered
        Then system will respond with "new user registered"

    Scenario: creation fails with already taken username and valid password
        Given command new is selected
        When username "pekka" and password "pekka1pekka" are entered
        Then system will respond with "new user not registered"

    Scenario: creation gails with too short username and valid password
        Given command new is selected
        When username "yo" and password "valid1valid" are entered
        Then system will respond with "new user not registered"

    Scenario: creation fails with valid username and too short password
        Given command new is selected
        When username "milla" and password "short" are entered
        Then system will respond with "new user not registered"

    Scenario: creation fails with valid username and password long enough but consisting of only letters
        Given command new is selected
        When username "kimmo" and password "pitkasalasana" are entered
        Then system will respond with "new user not registered"

    Scenario: can login with succesfully generated account
        Given user "eero" with password "sala1nen" is created
        And command login is selected
        When username "eero" and password "sala1nen" are entered
        Then system will respond with "logged in"
