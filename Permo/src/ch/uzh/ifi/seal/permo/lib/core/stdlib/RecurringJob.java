/*******************************************************************************
 * Copyright 2015 Software Evolution and Architecture Lab, University of Zurich
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/
package ch.uzh.ifi.seal.permo.lib.core.stdlib;

/**
 * A recurring job can be used to execute the same task repetitively in a given interval.
 */
public class RecurringJob extends Thread {

  private int interval;
  private final Runnable runnable;
  private boolean stopRequest;

  public RecurringJob(final int interval, final Runnable runnable) {
    super();
    setInterval(interval);
    this.runnable = runnable;
    this.stopRequest = false;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void run() {
    while (!stopRequest) {
      execute();
      sleep();
    }
  }

  /**
   * A request to stop the current {@link RecurringJob}. The job defines itself when its best to stop.
   */
  public void stopRequest() {
    this.stopRequest = true;
  }

  private void execute() {
    runnable.run();
  }

  private void sleep() {
    try {
      sleep(interval);
    }
    catch (final InterruptedException e) {
      stopRequest();
    }
  }

  /**
   * Sets the interval of the current {@link RecurringJob}.
   * 
   * @param interval
   *          the interval of the current {@link RecurringJob}
   */
  public void setInterval(final int interval) {
    this.interval = interval * 1000;
  }

}
