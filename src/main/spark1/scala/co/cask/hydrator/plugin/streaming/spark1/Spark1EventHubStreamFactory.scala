/*
 * Copyright © 2017 Cask Data, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package co.cask.hydrator.plugin.streaming.spark1

import org.apache.spark.streaming.StreamingContext
import org.apache.spark.streaming.dstream.DStream
import org.apache.spark.streaming.eventhubs.EventHubsUtils

/**
  * Spark 1 Event Hub Stream Factory
  */
object Spark1EventHubStreamFactory {

  def createEventHubStream(ssc: StreamingContext, eventHubNamespace: String,
                           eventHubName: String, policyName: String, policykey: String, partitions: String,
                           partition: String, dir: String, interval: String, consumerGroup: String,
                           offset: String): DStream[Array[Byte]] = {
    EventHubsUtils.createStream(ssc, Map[String, String] (
      "eventhubs.policyname" -> policyName,
      "eventhubs.policykey" -> policykey,
      "eventhubs.namespace" -> eventHubNamespace,
      "eventhubs.name" -> eventHubName,
      "eventhubs.partition.count" -> partitions,
      "eventhubs.checkpoint.dir" -> dir,
      "eventhubs.checkpoint.interval" -> interval,
      "eventhubs.consumergroup" -> consumerGroup,
      "eventhubs.filter.offset" -> offset
    ), partition)
  }
}