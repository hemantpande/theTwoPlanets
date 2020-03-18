# The Two Planets

_BattleTests.java_ - has all the unit tests.
_BattleRunner.java_ - is the console application that takes an input file and processes the input line by line.

#### How to build?
1. Unzip the solution
2. From terminal, run 

    `gradle build`
    
3. Executable jar will be created in `/build/libs/geektrust.jar`


#### How to run all the tests
1. Navigate to the folder and run

    `gradle clean test`

#### Review comments
1. Incorporated all the review comments. 
2. Fixed the defect in logic.
3. Added unit tests for all individual classes.
3. Added more descriptive output for tests. Example below:

`> Task :test 
 Executing test com.geektrust.tests.BattalionShould.correctly_calculate_the_counter_forces_needed_when_they_are_not_enough with result: SUCCESS
 Executing test com.geektrust.tests.BattalionShould.correctly_balance_the_previous_battalion with result: SUCCESS
 Executing test com.geektrust.tests.BattalionShould.not_balance_the_next_battalion_when_it_is_null with result: SUCCESS
 Executing test com.geektrust.tests.BattalionShould.correctly_calculate_the_counter_forces_needed_when_they_are_enough with result: SUCCESS
 Executing test com.geektrust.tests.BattalionShould.correctly_balance_the_next_battalion with result: SUCCESS
 Executing test com.geektrust.tests.BattalionShould.not_balance_the_previous_battalion_when_it_is_null with result: SUCCESS
 Executing test com.geektrust.tests.BattleShould.handle_reverse_substitution_rule_between_elephants_horses with result: SUCCESS
 Executing test com.geektrust.tests.BattleShould.handle_forward_substitution_rule_between_horses_elephants with result: SUCCESS
 Executing test com.geektrust.tests.BattleShould.handleAttack_with_zeroTanks with result: SUCCESS
 Executing test com.geektrust.tests.BattleShould.handle_substitution_choice_rule_between_elephants_horses with result: SUCCESS
 Executing test com.geektrust.tests.BattleShould.handleBasicAttack with result: SUCCESS
 Executing test com.geektrust.tests.BattleShould.handle_forward_substitution_rule_between_elephants_tanks with result: SUCCESS
 Executing test com.geektrust.tests.BattleShould.handle_reverse_substitution_rule_between_slingGuns_tanks with result: SUCCESS
 Executing test com.geektrust.tests.BattleShould.handle_attack_sample_1 with result: SUCCESS
 Executing test com.geektrust.tests.BattleShould.handle_attack_sample_2 with result: SUCCESS
 Executing test com.geektrust.tests.BattleShould.handle_attack_sample_3 with result: SUCCESS
 Executing test com.geektrust.tests.BattleShould.handle_attack_sample_4 with result: SUCCESS
 Executing test com.geektrust.tests.ArmyShould.handle_attack with result: SUCCESS
 Executing test com.geektrust.tests.PlanetShould.handle_attack with result: SUCCESS
 Executing test com.geektrust.tests.PlanetBuilderShould.construct_planet with result: SUCCESS
`