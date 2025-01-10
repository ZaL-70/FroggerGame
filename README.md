# Frogger Game: Software Overview
A recreation of a classic arcade game "Frogger" implemented in Java using JavaFX. This software represents a refactored repository (credits below) modfied to follow an MVC design pattern. It incorporates other game programming patterns like ECS (Entity Component System) while introducing new, original features beyond the orignial repository & classic arcade game. Guide the frog thorugh obstacles into homes without dying and achieve new high scores!

# Key features

## How to play
Firstly, clone the repository and open it through IntelliJ selecting the Gradle build system option. Either the free community edition or full version is fine. 
- Use keys WASD to guide the frog through various moving obstacles on the road and in the river
- Earn points by safely reaching 1 of the 5 homes at the end of the map.
- Beware! Dying will cost a life & points. If you lose all lives, the game ends.
- Win by taking all 5 homes on each level.
- Levels increase in difficulty introducing new challenges such as flying heron birds capturing the frog.

## Player features
Software includes facilities to manage the player (frog). 

### Animal/Frog (Player)
Type: An `Actor` called `Animal` defined as `GameObject` as a base.

Stores & manages:
- Level progress
- Score & lives
- Death state
- Player movement
- Player animations
- World interactions

`Animal` stores info and `Controller` class manages its logic components through external handler classes.

### Logic Handlers
External systems manage specific player logic, implemented as interface-based components for flexibility.

## Level features
The software provides tools for managing and customizing game levels (content displayed by the world).

### Setting up a level
- Use the `LevelSetup` class to manage base world levels & to define custom game levels.
- Use `GameConfig` file to configure all base game starting values e.g. player stats, obstacle speeds etc.

### Obstacles
Obstacles of the game are all either:
- A `GameObject` (imovable entity)
- An `Actor` (movable entity)

These objects can all have interactions with each other to decide logic of the game.

## World features
- Use `World` class to continually handle core model rendering & user input in real time to allow the game to operate properly both visually & functionally. 
- Use 'MyStage' class to handle custom behaviours of the UI & game world (e.g. sound effects, different UI screens etc)

## Game Loop
Facility to ensure the game runs seamlessly by applying necessary updates & logic handling as needed.

### Game Timer
`GameTimer` continually monitors the state of the game to:
- Updating the UI & world view
- End the game depending on the state. 

These are done via an external handler. 

### Game Logic Handler
`GameLogic` processes player & world states, applying relevant logic to maintain balance and progression.

# Credits
This project builds upon and refactors a repository created by Hirish Chandrasekaran - Hirish99. Enhancements, including the MVC and ECS patterns and new features, were implemented by Subhaan Afzal.

## Acknowledgments
Original concept and design: Arcade Game "Frogger" by Konami/Sega

Repository inspiration for:
- Base game: https://github.com/hirish99/Frogger-Arcade-Game
- UI: https://github.com/philliplagoc/Game-Engine

Framework and tools:
- JavaFX & TestFX
- Gradle Build System
- Mockito testing agent
