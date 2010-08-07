import twitter4j.TwitterFactory

/**
 * Created by IntelliJ IDEA.
 * User: Nik
 * Date: 01-Aug-2010
 * Time: 23:12:56
 * To change this template use File | Settings | File Templates.
 */

object TwitterApp extends Application {
  println("I'm a running application...")
  val twitter = new TwitterFactory().getInstance("pigsaw","-----");

  /* val statuses = twitter.getFriendsTimeline()
  println("Showing friends timeline...")
  statuses.foreach(status =>
    println(status.getUser.getName + ": " + status.getText))
  */

  println("My friends...")
  /*val friends_level_1 = twitter.getFriendsIDs.getIDs
  val friends_level_2 = for (friend <- friends_level_1) yield twitter.getFriendsIDs(friend).getIDs
  friends_level_2.foreach(println _)
  */

  twitter.getFriendsIDs.getIDs.foreach( friend_1 => {
    twitter.getFriendsIDs(friend_1).getIDs.foreach( friend_2 => {
      println("Your friend " + friend_1 + " has friend " + friend_2)
    })
    Thread.sleep(1000)
  })

  println("Finished")
}