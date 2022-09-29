Feature: User information

  This feature is responsible for testing the apis about the user information

  @TotalTest
  Scenario: Verify create a user
    Then I verify create a user and the result by get user
      | username  | firstname | lastname | email               | password | phone      |
      | user##### | Juan      | Perez    | juanperez@gmail.com | 12345678 | 9117918749 |

  @TotalTest
  Scenario: Verify delete a user
    Then I verify delete a user and the result by get user
      | username  | firstname | lastname | email                   | password | phone      |
      | user##### | Pepe      | Palotes  | pepitopalotes@gmail.com | 12345678 | 9117918749 |

  @TotalTest
  Scenario: Verify the user's login into the system
    Then I verify logs user by credentials
      | username  | firstname | lastname | email                   | password | phone      |
      | user##### | Pepe      | Palotes  | pepitopalotes@gmail.com | 12345678 | 9117918749 |

  @TotalTest
  Scenario: Verify the user's logout into the system
    Then I verify logs out user by credentials