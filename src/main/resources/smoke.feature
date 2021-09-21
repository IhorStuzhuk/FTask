Feature: Smoke
  As a user
  I want to test all main site functionality
  So that I can be sure that site works correctly

  Scenario Outline: Check add product to the Bag with size indication
    Given User opens '<homepage>'
    When User makes search by keyword '<keyword>'
    And User clicks search button
    And User goes to the 'ProductPage'
    And User checks that the 'ProductPage' is visibility
    And User selects size of the product
    And User clicks 'Add to bag' button
    And User opens '<bagPage>'
    Then User checks that the 'MyBag' page has <count> products

    Examples:
      | homepage                  | keyword | count | bagPage                  |
      | https://www.asos.com/men/ | Nike    | 1     | https://www.asos.com/bag |

  Scenario Outline: Check delete product from the Bag
    Given User opens '<homepage>'
    When User makes search by keyword '<keyword>'
    And User clicks search button
    And User goes to the 'ProductPage'
    And User checks that the 'ProductPage' is visibility
    And User selects size of the product
    And User clicks 'Add to bag' button
    And User opens '<bagPage>'
    And User checks that the 'MyBag' page has <count> products
    And User clicks remove button
    Then User checks the '<message>' is about product deleted from BagPage

    Examples:
      | homepage                  | keyword | count | bagPage                  | message           |
      | https://www.asos.com/men/ | Nike    | 1     | https://www.asos.com/bag | Your bag is empty |

  Scenario Outline: Check add product to the Bag without size indication
    Given User opens '<homepage>'
    When User makes search by keyword '<keyword>'
    And User clicks search button
    And User goes to the 'ProductPage'
    And User checks that the 'ProductPage' is visibility
    And User clicks 'Add to bag' button
    Then User checks that the '<errorMessage>' is displayed

    Examples:
      | homepage                  | keyword | errorMessage                                             |
      | https://www.asos.com/men/ | Nike    | Please select from the available colour and size options |

  Scenario Outline: Check sum of order after increasing quantity of product
    Given User opens '<homepage>'
    When User makes search by keyword '<keyword>'
    And User clicks search button
    And User goes to the 'ProductPage'
    And User checks that the 'ProductPage' is visibility
    And User selects size of the product
    And User clicks 'Add to bag' button
    And User opens '<bagPage>'
    And User checks that the 'MyBag' page has <count> products
    Then User increases quantity of product
    And User checks that '<sum of order>' was calculated correctly after increasing quantity of product

    Examples:
      | homepage                  | keyword | count | bagPage                  | sum of order |
      | https://www.asos.com/men/ | Nike    | 1     | https://www.asos.com/bag | 136.00       |

  Scenario Outline: Check that the url of 'Search result' page matches the valid query
    Given User opens '<homepage>'
    When User makes search by keyword '<keyword>'
    And User clicks search button
    Then User checks that the url of 'Search result' page contains '<query>'

    Examples:
      | homepage                  | keyword | query  |
      | https://www.asos.com/men/ | Nike    | q=nike |

  Scenario Outline: Check that the 'Search result' page displays a message stating that nothing was found after an invalid query
    Given User opens '<homepage>'
    When User makes search by keyword '<keyword>'
    And User clicks search button
    Then User checks that the 'Search result' page displays a '<message>'

    Examples:
      | homepage                  | keyword   | message         |
      | https://www.asos.com/men/ | asdfhgdah | nothing matches |

  Scenario Outline: Check that FaceBook page is opened after clicking the social network icon
    Given User opens '<homepage>'
    When User clicks the Facebook icon
    Then User checks that the '<FaceBook>' page is open

    Examples:
      | homepage                  | FaceBook                       |
      | https://www.asos.com/men/ | https://www.facebook.com/ASOS/ |

  Scenario Outline: Check clear the history of search
    Given User opens '<homepage>'
    When User makes search by keyword '<keyword>'
    And User clicks search button
    And User checks that the 'ProductPage' is visibility
    And User makes search by keyword '<keyword2>'
    And User clicks clear button
    Then User checks that the history of search is cleared
    Examples:
      | homepage                  | keyword | keyword2 |
      | https://www.asos.com/men/ | Nike    |          |

  Scenario Outline: Check add product to the 'Saved List'
    Given User opens '<homepage>'
    When User makes search by keyword '<keyword>'
    And User clicks search button
    And User clicks 'Add to saved List' button
    Then User opens '<savedListPage>'
    And User checks that the 'Saved List' page has '<count>' products

    Examples:
      | homepage                  | keyword | count | savedListPage                    |
      | https://www.asos.com/men/ | Nike    | 1     | https://www.asos.com/saved-lists |

  Scenario Outline: Check delete product from the 'Saved List'
    Given User opens '<homepage>'
    When User makes search by keyword '<keyword>'
    And User clicks search button
    And User clicks 'Add to saved List' button
    And User opens '<savedListPage>'
    And User checks that the 'Saved List' page has '<count>' products
    Then User clicks 'Delete from saved List' button
    And  User checks that the 'Saved List' page displays a '<message>'

    Examples:
      | homepage                  | keyword | count | savedListPage                    | message                 |
      | https://www.asos.com/men/ | Nike    | 1     | https://www.asos.com/saved-lists | You have no Saved Items |


  Scenario Outline: Check ASC sorting products on the SearchResultPage
    Given User opens '<homepage>'
    When User makes search by keyword '<keyword>'
    And User clicks search button
    Then User clicks ASC sorting
    And User checks that products are sorted by ASC

    Examples:
      | homepage                  | keyword |
      | https://www.asos.com/men/ | Nike    |

  Scenario Outline: Check that the logo moves the user to the home page
    Given User opens '<BagPage>'
    When User clicks on logo
    Then User checks that the '<homepage>' is opened
    Examples:
      | homepage             | BagPage                  |
      | https://www.asos.com | https://www.asos.com/bag |

  Scenario Outline: Check that the price is displayed correctly taking into account the discount
    Given User opens '<homepage>'
    When User makes search by keyword '<keyword>'
    And User clicks search button
    Then User checks that the price is displayed correctly taking into account the discount
    Examples:
      | homepage                  | keyword |
      | https://www.asos.com/men/ | Nike    |

