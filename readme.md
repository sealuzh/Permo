# Permo: A performance monitoring plugin for the Eclipse IDE

## License

Copyright 2015 Software Evolution and Architecture Lab, University of Zurich

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

 http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.


## Introduction

This is a research project by the [Software Evolution and Architecture Lab](http://www.ifi.uzh.ch/seal.html) at the [Department of Computer Science](http://www.ifi.uzh.ch/index.html) of the [University of Zurich](http://www.uzh.ch/index_en.html). Interested users can check out the corresponding [research paper](https://peerj.com/preprints/985.pdf) to learn more about our research.

The aim of this project is to integrate runtime monitoring data from production deployments of the software into the tools developers utilize in their daily workflows (i.e., IDEs) to enable tighter feedback loops. We refer to this notion as **feedback-driven development (FDD)**.

In this project, the abstract FDD concept is instantiated in the form of a plugin for the Eclipse IDE.  


## Requirements

Please make sure you have [Maven](https://maven.apache.org/) installed on your system and have a distribution of the [Eclipse IDE](https://eclipse.org/downloads/).


## Building

You will find the Java source code in the submodule named `Permo`. There is a submodule called `Permo-Build`, which is responsible for building the entire plugin via Maven. Open a terminal in the `Permo-Build` directory and run

`mvn install`

to build the entire project.

## Installing the plugin

To install the plugin into your Eclipse IDE, you will need an update site for the plugin. The build process explained above produces a plugin update site in
`Permo-Updatesite/target/repository/`.

To install the produced Eclipse plugin into your local IDE instance, open Eclipse, open the **Help Menu**, select the entry **Install New Software**. In the newly opened window, click on **Add** and then choose **Local..** on the dialog. Now you can browse to the location of the `Permo-Updatesite/target/repository/`  folder, and select it as a root folder for the plugin. Give it an appropriate name (such as PerformanceHat), click **OK**, and then check all checkboxes that appear, and click on **Next** to proceed and finish the installation procedure.

## Usage

### Monitoring view

One of the views the plugin provides is the performance monitoring view, which lets you inspect the performance history of a method in an interactive chart. To use this feature, select a the method of interest in the Java Editor (through placing the cursor on it) or in the Outline View. Then, right-­click and choose ​
**Monitor Method**. Alternatively, you can use **Ctrl+M** as a shortcut. The opened view allows to be customized by selecting different time ranges etc.
