Feature: Pets information

  This feature is responsible for testing the apis about the pet information

  @TotalTest
  Scenario: Verify add a new pet
    Then I verify add a new pet and the result on the search by id
      | category name | pet name | tag | status    |
      | cat           | michi    | mim | available |

  @TotalTest
  Scenario: Verify update a pet
    Then I verify update a pet and the result on the search by id
      | category name | pet name | tag | status    | new category | new name | new tag | new status |
      | dog           | boby     | bob | available | cat          | michi    | mich    | pending    |

  @TotalTest
  Scenario: Verify delete a pet
    Then I verify delete a pet and the result on the search by id
      | category name | pet name | tag | status    |
      | rabbit        | bunny    | bun | available |

  @TotalTest
  Scenario: Verify the search by status
    Then I verify add a new pet and the result on the search by status
      | category name | pet name | tag | status    |
      | cat           | michi    | mim | pending |