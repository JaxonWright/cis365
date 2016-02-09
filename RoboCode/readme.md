##**CIS 365 Project 1: AI Development in Virtual Robotics**

###**Names: Jaxon Wright & Zack Patterson**

**Due Date**

-   at the start of class on Tuesday, February 9.

**Before Starting the Project**

-   Read the entire project description before starting. A team may only
    consist of exactly two students.

**Learning Objectives**

After completing this project you should be able to implement AI
algorithms in the areas of: event processing and vision, tracking
external agents, agent navigation, or decision making in uncertain/fuzzy
environments.

**Rubric**

10 pts appropriate documentation 

10 pts elegant source code 

25 pts approach/design 

25 pts implementation

30 pts results during testing 

**Option 1)** Develop a robot that is an ideal opponent to one single
class of the sample robots.

-   Pick one of the sample robots (Corners, Crazy, Fire, Painting,
     **RamFire**, SpinBot, Target, Tracker, TrackFire, VelociRobot,
     or Walls).

-   Do not select Interactive, SittingDuck, or a team robot.

-   Watch the robot in action, review its source code, and determine its
     strengths and weaknesses.

-   Design a dominant strategy for eliminating this opponent.

-   Develop a robot that can consistently defeat this opponent in a
    1-on-1 battle.

-   Document your observations, design, overall strategy, and an honest
    assessment of your success in the documentation for your robot.

Test your robot in 500 1v1 battles with this opponent (800x600, 500
rounds, .1 gun cooling rate, 450 inactivity time). You may wish to
minimize the GUI for better performance. Report the ‘Total Score’ and
‘Bullet Dmg’ in your documentation for this test. Do not include any
other robots in this testing.



###Robot Name: RamKiller

**Design Decisions**

-   *Running Away*

> The first idea we had was to run away from RamFire to avoid its deadly
> ram damage. We decided to add the ability to fire while RamFire was
> stationary/scanning to get some damage in. Unfortunately RamKiller
> could not kill RamFire before running away into a wall.

-   *Box Movement*

> Our next idea for avoiding walls (and staying away from collisions
> with RamFire) was to simply move between four corners of an imaginary
> box whose edges were a safe distance from the edges. This was wildly
> successful and lead us to RamKiller’s first set of matches where it
> won more than it lost.

-   *Running on proximity*

> Due to the number of times RamKiller collided with RamFire we decided
> to have RamKiller run when it was too close. Unfortunately,
> onHitRobot() only activates when RamKiller hits RamFire, which should
> be never, and onHitByBullet() would be activated too much,
> interrupting itself if we tried any movements that took more than one
> turn. To avoid simply backing into walls we scrapped these two methods
> and instead checked RamFire’s distance on scanning it to turn
> perpendicular and move to a new point if it was to close.

-   *Moving to Furthest point*

> We developed this for triangle movement, to make sure that RamKiller
> would not go to a point that RamFire was at, by selecting the one that
> was furthest from him. We found some code on stackoverflow that used
> some basic trigonometry to obtain RamFire’s location. We eventually
> incorporated and improved this feature for the Box Movement technique.

-   *Avoiding Opposite Corners*

> We developed this technique to avoid moving diagonally across the map
> where RamFire had a chance to intercept RamKiller. Somewhat
> surprisingly, this technique brought us our greatest gain, bringing
> our win rate from 80% to 97%.

-   *Abandoned Ideas*

> We tried many ideas that ultimately didn’t work. Some, like
> trying to map the walls, were completely abandoned, while others
> like perpendicular movement were eventually incorporated into other
> techniques. Others were ultimately unused but allowed us to see a
> problem, such as triangular movement allowing us to see the problem of
> being intercepted while crossing the map.

**Results**

We averaged around 484 to 488 out of 500. This is a *97% success rate*
