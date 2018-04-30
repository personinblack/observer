package me.blackness.observer.source;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import me.blackness.observer.Source;
import me.blackness.observer.Target;

/*
       .                                                    .
    .$"                                    $o.      $o.  _o"
   .o$$o.    .o$o.    .o$o.    .o$o.   .o$$$$$  .o$$$$$ $$P  `4$$$$P'   .o$o.
  .$$| $$$  $$' $$$  $$' $$$  $$' $$$ $$$| $$$ $$$| $$$ ($o  $$$: $$$  $$' $$$
  """  """ """  """ """  """ """  """ """  """ """  """  "   """  """ """  """
.oOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOo.
  ooo_ ooo ooo. ... ooo. ... ooo.  .. `4ooo.  .`4ooo.   ooo. ooo. ooo ooo.  ..
  $$$"$$$$ $$$| ... $$$| ... $$$$$$ ..    "$$o     "$$o $$$| $$$| $$$ $$$|   .
  $$$| $$$ $$$|     $$$|     $$$|     $$$: $$$ $$$: $$$ $$$| $$$| $$$ $$$|
  $$$| $$$ $$$| $o. $$$| $o. $$$| $o. $$$| $$$ $$$| $$$ $$$| $$$| $$$ $$$| $.
  $$$| $$$ $$$| $$$ $$$| $$$ $$$| $$$ $$$| $$$ $$$| $$$ $$$| $$$| $$$ $$$| $o.
  $$$| $$$ $$$| $$$ $$$| $$$ $$$| $$$ $$$| $$$ $$$| $$$ $$$| $$$| $$$ $$$| $$$
  $$$| $$$  $$. $$$  $$. $$$  $$. $$$ $$$| $$$ $$$| $$$ $$$| $$$| $$$  $$. $$$
  $$$: $P'  `4$$$Ü'__`4$$$Ü'  `4$$$Ü' $$$$$P'  $$$$$P'  $$$| $$$: $P' __`4$$$Ü'
 _ _______/∖______/  ∖______/∖______________/|________ "$P' _______/  ∖_____ _
                                                        i"  personinblack
                                                        |
 */

public final class BasicSourceTest {
    @Test
    public void canBeSubscribed() {
        final StringBuilder toBeChanged = new StringBuilder();
        final Source<String> source = new BasicSource<>();
        final Target<String> target = new Target<String>() {
            @Override
            public void update(String argument) {
                toBeChanged.append(argument);
            }
        };
        source.subscribe(target);
        final String change = "change";
        source.notifyTargets(change);
        Assertions.assertEquals(change, toBeChanged.toString());
    }

    @Test
    public void canBeUnsubscribed() {
        final StringBuilder toBeChanged = new StringBuilder();
        final Source<String> source = new BasicSource<>();
        final Target<String> target = new Target<String>() {
            @Override
            public void update(String argument) {
                toBeChanged.append(argument);
            }
        };
        source.subscribe(target);
        final String change = "change";
        source.notifyTargets(change);
        source.unsubscribe(target);
        source.notifyTargets(change);
        Assertions.assertEquals(change, toBeChanged.toString());
    }

    @Test
    public void cannotBeSubscribedMultipleTimes() {
        final StringBuilder toBeChanged = new StringBuilder();
        final Source<String> source = new BasicSource<>();
        final Target<String> target = new Target<String>() {
            @Override
            public void update(String argument) {
                toBeChanged.append(argument);
            }
        };
        source.subscribe(target);
        source.subscribe(target);
        final String change = "change";
        source.notifyTargets(change);
        Assertions.assertEquals(change, toBeChanged.toString());
    }

    @Test
    public void multipleTargetsCanSubscribe() {
        final StringBuilder toBeSame = new StringBuilder();
        final Source<String> source = new BasicSource<>();
        final Target<String> firstTarget = new Target<String>() {
            @Override
            public void update(String argument) {
                toBeSame.append(argument);
            }
        };
        final Target<String> secondTarget = new Target<String>() {
            @Override
            public void update(String argument) {
                toBeSame.delete(0, argument.length());
            }
        };
        source.subscribe(firstTarget);
        source.subscribe(secondTarget);
        source.notifyTargets("change");
        Assertions.assertEquals("", toBeSame.toString());
    }
}
