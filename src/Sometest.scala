import org.scalatest.Spec
import org.scalatest.matchers.ShouldMatchers

/**
 * Created by IntelliJ IDEA.
 * User: Nik
 * Date: 01-Aug-2010
 * Time: 23:02:41
 * To change this template use File | Settings | File Templates.
 */

class Sometest extends Spec with ShouldMatchers {
  describe("Something") {
    it("Should add two numbers") {
      1+2 should be === 3
    }
  }
}