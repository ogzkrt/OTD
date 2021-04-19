![Build Workflow](https://github.com/ogzkrt/OTD/actions/workflows/gradle.yml/badge.svg)


# OTD

OTD is a tower defense game written with Java by using LibGDX. Game works both on Android and Desktop (tested on Ubuntu,MacOS,Windows).

Here is the game play running on the Android emulator.

<p align="center">
<img src="https://user-images.githubusercontent.com/6486180/113852181-f3769e00-97a4-11eb-96bc-4af944b0d409.gif" alt="Game Play GIF" width="800">
</p>

This is the desktop gameplay.

<p align="center">
<img src="https://user-images.githubusercontent.com/6486180/113862614-4c4c3380-97b1-11eb-982e-2d6c8539ca09.gif" alt="Game Play GIF" width="800">
</p>

## Installation

Clone this project with following command:

```git
git clone https://github.com/ogzkrt/OTD.git
```

Then import it as a Gradle project with IDE of your choice. It has been tested both with Eclipse and Android Studio.

or

Download the desktop-1.0.jar from the **/jars** folder and execute the following command.
```bash
java -jar desktop-1.0.jar
```


## Usage

Game consist of three part which are.

- Score Board ( On the top )  
- The maze that we are going to build our towers. ( Center ) 
- Controlling Menu ( On the bottom ) 
 
### Score Board

Score board is located on the top. It allows you to see **Damage**,**Attack Speed** and **Range** of the currently selected tower. It also shows the current **Score** and **Money**.

### The Maze

This is the place that we are going to build our towers.  You are not allowed to build towers to the path which enemies are following.

### Controlling Menu

On the bottom there is a menu which allows you to control the game. This menu also consist of three part.

#### Tower Buttons

On the bottom left, you can see three button which you can drag and drop to build a tower. Currently three type of tower exist.


- <img src="https://github.com/ogzkrt/OTD/raw/master/images/images/fire_plane_menu.png"  width="75">
Fire Tower  (Projectile Shooting) 
- <img src="https://github.com/ogzkrt/OTD/raw/master/images/images/ice_tower_menu.png"  width="75">
 Ice Tower   (Projectile Shooting, also slows down the enemies)
- <img src="https://github.com/ogzkrt/OTD/raw/master/images/images/menu_item_electric.png"  width="75">
 Electricity Tower (Continuous Shooting)

#### Gameplay Buttons

These buttons controls the state of the game.
- <img src="https://github.com/ogzkrt/OTD/raw/master/images/images/resume_menu_item.png"  width="50">
 You can pause and resume the game.
- <img src="https://github.com/ogzkrt/OTD/raw/master/images/images/menu_item_2x.png"  width="50">
When you are so sure that you can clear the wave, you can speed up the game.
- <img src="https://github.com/ogzkrt/OTD/raw/master/images/images/btn_remake.png"  width="50">
You can restart the game with another random maze, if you don't like the current one.
- <img src="https://github.com/ogzkrt/OTD/raw/master/images/images/btn_quit.png"  width="50">
You can go to the Menu State.

#### Upgrade Buttons

These buttons can upgrade selected towers if you have enough money.
- <img src="https://github.com/ogzkrt/OTD/raw/master/images/images/attack_menu.png"  width="75">
Attack (Increase the damage of the selected tower, after every usage it's price doubles up.)
- <img src="https://github.com/ogzkrt/OTD/raw/master/images/images/range_menu.png"  width="75">
Range (Increase the range of the selected tower, after every usage it's price doubles up.)
- <img src="https://github.com/ogzkrt/OTD/raw/master/images/images/speed_menu.png"  width="75">
Attack Speed (Increase the attack speed of selected the tower, after every usage it's price doubles up.)



## Contributing
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

Please make sure to update tests as appropriate.


## License
[MIT](https://choosealicense.com/licenses/mit/)
