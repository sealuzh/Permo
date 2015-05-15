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
package ch.uzh.ifi.seal.permo.performance.main.ui.trigger;

import ch.uzh.ifi.seal.permo.common.ui.trigger.AbstractViewTrigger;
import ch.uzh.ifi.seal.permo.performance.main.ui.view.PerformanceView;
import ch.uzh.ifi.seal.permo.performance.timeseriers.core.model.MonitoringData;
import ch.uzh.ifi.seal.permo.performance.timeseriers.core.model.serializer.MonitoringDataSerializer;
import ch.uzh.ifi.seal.permo.performance.timeseriers.core.model.serializer.MonitoringDataSerializerImpl;

/**
 * A trigger (see {@link AbstractViewTrigger}) for the Performance View.
 */
public class PerformanceViewTrigger extends AbstractViewTrigger<PerformanceView> {

  private static final String PROJECT_HISTORY_VIEW__ID = "ch.uzh.ifi.seal.permo.views.Performance";

  private MonitoringData monitoringData;

  private MonitoringDataSerializer monitoringDataSerializer;

  public PerformanceViewTrigger(final MonitoringData monitoringData) {
    this.monitoringData = monitoringData;
    this.monitoringDataSerializer = new MonitoringDataSerializerImpl();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected String viewId() {
    return PROJECT_HISTORY_VIEW__ID;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected String secondaryViewId() {
    return monitoringDataSerializer.serialize(monitoringData);
  }

  @Override
  protected boolean allowMultipleViews() {
    return true;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected boolean isContentAvailable() {
    return monitoringData != null;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void updateContent(final PerformanceView view) {
    view.updateView(monitoringData);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void clearContent(final PerformanceView view) {
    view.clearView();
  }

}
