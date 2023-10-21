# Selectlative
A Forge mod for Minecraft

### Setup
Put it in `GameRootFolder/mods`

### Config
The files available for configuration are located at `GameRootFolder/Config/Selectlative`
Please edit according to the JSON syntax specification.
* Config.json
  * `Advancement_List` A list, fill in the ID of the achievements that need to be shared.
  * `Advancement_LogInfo` Control the output of advancement ID when player was granted in Debug log.
* Data.json
  * Store progress that has already been granted to players.
  * You can manually change the existing true to false, and default to false if not specified in the file.
    