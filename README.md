# Greedy-Pathfinder
A topographic map is a two-dimensional representation of a terrain, usually utilizing contour lines to represent changes in elevations. A digital representation of a topographic map samples elevations at regular intervals, essentially representing the continuous terrain as a two-dimensional grid of discrete elevations. The National Oceanic and Atmospheric Administration maintains a large database of topographic maps, and even [provides a free tool for generating digitized topographic maps](https://maps.ngdc.noaa.gov/viewers/grid-extract/index.html).

Greedy Pathfinder is a Java project that takes these digitized topographical maps and uses a greedy algorithm in an attempt to find the path with the least change in elevation. The greedy algorithm for choosing a path across a map will always select the next step that represents the smallest change in elevation until it traverses the map. This is repeated for each potential starting point along the map. After completing this process, the path with the least change in elevation is selected.

In the final display, the various initial paths traced by the greedy algorithm are highlighted green, and the final path with the least change in elevation is highlighted red.

Also provided are sample maps of varying size.
