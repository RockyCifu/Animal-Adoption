# Adopt a Pet!

We at Launch Academy are big fans of pets. There are many pets out there looking for a home. Your challenge is to build an app that allows good pets to find their ultimate destination! Wait... does this seem familiar? It should! This challenge will serve as an assessment of what you've learned throughout the program, with an emphasis on Spring. You will be taking your work from the Node Group Project and refactoring the Express backend to run on Spring! To get started, run the following commands:

```no-highlight
dropdb adopt_a_pet
createdb adopt_a_pet
cd challenges
et get java-spring-express-refactor
cd java-spring-express-refactor
```

Update your `Seeder.js` with initial data:

You can find your seeder in `src/main/frontend/server/db/Seeder.js`
The `INSERT INTO` part of the query is built for you, but you will need to provide the values.

```no-highlight
yarn run db:import
yarn run db:seed
```

Next, we'll boot up the original Node app by running the following **from within your `java-spring-express-refactor/src/main/frontend` directory**:


```no-highlight
yarn install
yarn run dev
```

***NOTE The yarn run dev is only needed to start your express server to ensure that things are working at the beginning. Once you begin using Spring to serve your data you do not need to `yarn run dev` any further.***

Open a second terminal instance and run:

```no-highlight
yarn run dev:client
```

The provided code models the Meets Expectations portion of the project. Your job is to replace the back end with `Spring` and for the provided React code to work with no changes needed.
You _may_ update the React code as you see fit.


## Meeting Expectations

The original user stories can be found here:
[Group Project](https://learn.launchacademy.com/teams/boston-apprenti-4/curricula/apprenti-4/lesson_groups/week_10:_node_group_project/lessons/node-group-project)

There is a help ER Diagram as well as a React Component tree you can find in `src/main/frontend/client/public/resources`

We recommend tackling things in the same order as the original project.

* Pet Types
* Pets of a Specific Type
* Individual Pets
* Pet Surrender


Within each of those you will need:

Unless otherwise specified:
- All data must be served from a Spring Rest Controller to a React Component
  - Make sure to create new migrations when creating or editing tables in your database
  - All entities should have validation at the model level as well as the database level where possible

**_HINT: For each Java Class you will need each of the following:_**

- Model
- Rest Controller
- Service
- Repository


## Exceeds Expectations

### Apply to Adopt

The code for Express based Adoption Applications has been provided. Update your Spring back end to serve the necessary information to React.

For the final user stories you will need to dust off your React Skills and write both the Spring and React necessary to fulfil them.

### Update an Application

```no-highlight
As a user
I want to be able to edit my application
So that I can keep the shelter up to date
```

Acceptance Criteria

- Navigating to `/pending_applications` should show me applications where the status is pending
- There should be a delete button which prompts the user to confirm that they want to delete their application. Upon confirmation the record should be deleted from the database.
- There should be an edit button which takes me to a form pre-populated with the information for the request. This form should look identical to the `new` form
  - Upon submission the existing record should be updated and no new record created

### Review an Adoption Request

```no-highlight
As an employee of the adoption agency
I want to have a form to review adoption requests
So that I can place a pet in the right home
```

Acceptance Criteria

- Create a form for an admin (using routes, not authentication) to approve or deny an adoption request
  - Form submission updates the pending request in the database to `approved` or `denied`
  - Form submission also updates the `adoption_status` on the `adoptable_pets` table
- The form page should contain all information about pet and applicant
- Update the specific Animal Index Pages to only display animals who have a `null` or `denied` adoption status
- Create a new Index page for animals which have been successfully adopted
  - This should display animals of all types which have an adoption status of `approved`

```no-highlight
  As an employee of the adoption agency
  I want to have a form to review surrender applications
  So that I can determine if we can take the pet
```

Acceptance Criteria

- Create a form for an admin (using routes, not authentication) to approve or deny a request to list an animal for adoption
  - Form submission updates the pending request in the database to `approved` or `denied`
  - Form submission also creates an entry in the `adoptable_pets` table for the approved animal
- When a request to list an animal is approved that animal should then appear on the relevant Specific Animal Index Page
