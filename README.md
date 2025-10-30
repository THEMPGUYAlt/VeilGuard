# VeilGuard

VeilGuard is an advanced, packet-based Anti-Xray and behavior analysis plugin for Paper and Spigot servers (versions 1.8.8 through 1.21.x). It detects players using X-Ray clients or automation tools by combining packet monitoring, fake visual data, and heuristic analysis.

## Features

- **MineNotify**: Alerts staff when a player discovers tracked ores like diamonds.  
- **MinePattern**: Detects unnatural mining paths directly toward ore clusters.  
- **MineSight**: Spawns hidden packet-based villagers inside blocks to detect wall vision.  
- **BaritoneCheck**: Monitors rotations and mining patterns for automated behavior.  
- **FakeOreBait**: Sends fake ore blocks to players; mining them triggers suspicion.  
- **RayTraceCheck**: Tracks player eye vectors toward hidden ores.  
- **DensityAnomaly**: Compares mined ore-to-stone ratios with server averages.  
- **TimeReaction**: Measures the time between seeing an ore and mining it.  
- **Automatic Heuristic System**: Aggregates multiple checks into a suspicion score for alerts or punishments.  
- **ProtocolLib Integration**: Automatically detects ProtocolLib and uses it if available.

## Installation

1. Place `VeilGuard.jar` in your server's `/plugins/` directory.  
2. (Optional) Install ProtocolLib for enhanced detection.  
3. Restart the server. VeilGuard will detect ProtocolLib automatically if present.  
4. Configure `config.yml` to adjust thresholds, punishments, and enabled checks.

## Configuration

Example configuration snippet:

```
settings:
  alert-threshold: 60
  autoban-threshold: 90
  decay-rate: 0.1
  punishment-command: "ban %player% X-Ray detected (VeilGuard)"
  use-protocollib: auto

checks:
  MineNotify: true
  MinePattern: true
  MineSight: true
  Baritone: true
  FakeOreBait: true
  RayTraceCheck: true
  DensityAnomaly: true
  TimeReaction: true
```
