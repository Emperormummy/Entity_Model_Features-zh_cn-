

RC-4
- axolotl tail part OptiFine part name mapping fixed
- removed now unnecessary config settings
- fixed emissive textures on CEM models acting weird in RC-3
- some minor optimizations and code cleanups
- moved animation execution code into the relevant parent bone to match OptiFine execution order (solved flickering models in inventory, and improved efficiency slightly)
- leaving the config screen now only triggers a resource reload if a setting has been changed

RC-3
- re-enabled an option for testing in RC-3 that allows using minecraft's internal MathHelper class for some math methods as opposed to regular Java's Math methods *(doesn't really seem to be a performance benefit in ym testing, just trialling this before I decide whether to delete this old code)*
- fixed top level jem texture overrides
- fixed book.jem's flipping page pivots
- changed some default config settings for performance boosts
- fixed a forge crash
- fixed armor stand armor models not remapping the headwear part
- fixed guardian and elder_guardian OptiFine part name mappings ("eye" did not work)

RC-2
- added a random entity display to the config screen
- fixed null pointer exception with model overrides
- fixed ravager jaw having the wrong OptiFine part name
- fixed is_on_shoulder animation variable
- fixed wolf_collar.jem loading
- fixed an accidental limitation where a vanilla part unknown to the OptiFine part name map would be targeted by animations intended for custom parts *(in FA this was parrot heads and witch hats)*
- fixed a crash with unknown models building illegal paths
- fixed an issue with parts with empty ids using "this" animation key
- fixed book models
- fixed chest & shulker model mixups
- fixed animations not being read from models with parts with duplicate ids *(fixed several of Creepermax123's packs)*

RC-1
- OptiFine CEM appears to be officially solved in EMF and seems to be fully working for almost every single use case :)
- large rework, reimplementation, and internalisation of animation code and model variant checking. 
preventing all occurrences of animation de-sync and fixing animations for all modded/replaced entities/models and
block entities.
- various stability fixes to model variants including texture support.
- block entities fully supported *(expect a few missalignments due to possible OptiFine part name differences, report these if you can , `e.g. lectern and enchating table books might be swapped`)*
- physics mod compat can now choose to try render the vanilla model parts or the custom EMF model parts
- should no longer cause `part not found` crashes or model failures
- reduced the log message length of model loading errors
- fixed emf/ & mobName/mobName directory issues
- animations now support model part name hierarchy as in OptiFine. i.e   `left_arm:hand:finger1` works to separate part `finger1` in the `left_arm` model group from the `right_arm` one
- warning structure added to detected model overrides prompting to disable setting if it crashes or to warn users of models modified by other mods
- added OptiFine name format support for all previously missing entities including sniffer and camel and block entities
- added support for render variables in animations `e.g. render.shadow_size`
- expanded animation model variable support to block entities, seemingly matching OptiFines output for these.
- block entity animations support variables "var.???"
- added various render modes for custom parts, `NORMAL, GREEN FLASHING, WIREFRAME, OFF`, will not affect vanilla parts.
- so much more i've probably forgotten some.


0.2.13

- fixed `IndexOutOfBoundsException` & `already building` crashes related to texture overrides in sub-models
- added an option to select how to handle texture overrides on sub-model parts, whether to rely on EMF's rendering code, an Iris rendering quirk, or to disable them.*(this should almost always be left on default except when debugging texture overrides in sub-models)*


0.2.12

A bug fix update before I backport down to 1.19

- added a workaround to render the vanilla models parts when an entity dies for physics mod compatibility *(THIS IS FAR FROM PERFECT and really just a test)*
- prevented animation variables from invalidating expressions during division testing by defaulting to 0 during setup phase
- tweaked the config screens into categories and altered translations
- extended the option to the setting to print out all unknown model information, to also try and make an example .jem file for that entity model *(it is not a perfect match, they are meant as a starting point for you)*
- fixed the option to try and force EMF models back onto modified entities, triggering for entities without emf models
- fixed limb_speed idling at the minimum float value it should now idle at 0 instead
- improved texture overrides when used internally within models *(no longer relies on a broken behaviour of iris)* *(will have lighting glitches when used on mobs that have glowing eyes or ETF emissive textures until ETFs next update)*



0.2.11
Did someone say mod compatibility?
This update adds a few features regarding `EMF` and other mods the most notable of which being an initial trial of modded entity support.
The following rules for modded entities are subject to change at any time during the beta.
As a rule of thumb all modded entities jem files will need to be placed at `optifine/cem/modded/{NAMESPACE}/{MOBNAME}.jem`
To see if a modded entity can be altered by EMF enable the option to print modded entity model details to the game log, 
if it shows up in the log it will give you the .jem file path and all the root part names and their default values to work with.
If the mod creator for some reason gave the entity a different type name from its model name they will not currently animate, I have some plans for this eventually.

Secondly this change opens up the entirety of the minecraft entity model loading system to be customized via jem files safely without restriction,
for example I have yet to add any code specifically to model or animate signs but the modded printout option will reveal that 
`optifine/cem/sign/oak.jem` is a detected valid model file for signs now, this will be true for many unexpected vanilla models 
such as `elytra` and `block entities`. **HOWEVER** if they appear in this printout it means they are effectively "unknown" models,
they **DO NOT** yet use the OptiFine part names and are very unlikely to animate. 
The OptiFine part names are something I need to add manually because they differ from the internal code names.
And all of these modded or unknown models that are not the main model of a LivingEntity need to have animation support manually added in the code.

There is also a new option, enabled by default, to try and force the game to use the CEM models for vanilla entities even 
if another mod has tried to replace them, I'm not too sure how well this will work with all mods, but it seems to work nicely with the few I've tested with

- added modded entity support and, as a byproduct, "model only" support for every single entity model in the vanilla game
- added an option to print out part_name and default pivot/rotation/scale details for all modded or unknown entity models
- added an option to try and force `EMF` models to not be overridden by other mods *(probably won't work with all mods)*
- fixed several issues with variant models breaking *(random models feature)*
- fixed vanilla model rendering option not working on forge

0.2.10
- 1.20 update
- fixed an issues with the max() animation method
- added a temporary config option to experiment with how EMF handles resetting its data (intended to be used to troubleshoot with users when a known bug occurs that I haven't yet been able to replicate myself)


0.2.9
- emf/cem/ override directory added, .jem files here will be used before optifine/cem/ is checked
- optifine/cem/<entity_name>/... directory support added
- .jpm positioning vastly improved, now Fresh Animations official addon pack fully works, some others don't fully work
- now requires ETF 4.4.4 or newer
- enabled some block entities for testing (mob heads,shulker boxes, and single chests) they don't animate
- mapped some other optifine name differences to fix these models: llama spit, shulker bullets


0.2.8
- made ETF a required dependency *(Consider EMF now as an ETF addon, allowing you to still use other model mods without losing ETF)*
- EMF now has a basic config screen on both Fabric and Forge 
- added model texture override support
- re-added option to render the vanilla model underneath the custom one, or offset from it *(they will not animate and are meant to help pack makers position their models)*
- random model variations now update according to ETF's texture update rate setting
- fixed `frame_time` value it was about 2 times too large resulting in some animation transitions or counters playing faster
- added animation variables `day_time`, `day_count`, and tweaked `time`
- added `anger_time_start` and included tickDelta smoothing in `anger_time`
- fixed logical error in animation math expressions where "!(boolean)" was different to "!boolean"
- green render mode now pulses to allow accurate viewing of texture colours
- added an implementation of `move_forward` & `move_strafing` which are broken in OptiFine, in EMF these values relate 
how much the entities total movement is in a particular direction. Using a players [wasd] movement as reference a 
player holding `W` has a `move_forward` of 1, and a player holding `S` has a `move_forward` of -1, and a player 
holding `D` has a `move_strafing` of 1, and a player holding `A` has a `move_strafing` of -1, and a player holding `WD`
has a `move_forward` & `move_strafing` of roughly 0.7 in both . this value is independent of speed and is intended as a measurement 
of the directionality of an entities' movement.



0.2.7

- fixed all remaining base Fresh animations issues, **yes ***ALL*** of them**, *(the addons are still broken as are .jpms)*
- fixed several positional issues with models in general, almost every default generated model from blockbench matches exactly in game
- fixed flickering in pause menu for animations that factor in tick delta
- fixed texture size issues plaguing wardens and guardians in their blockbench default models
- added wither and ender dragon support, *(ender dragon doesn't support custom animations yet and when they do they will be limited)*

0.2.6

- fixed an issue with the head_yaw constraint fix in 0.2.5
- ensured floating point value consistency across all animation expression code
- fixed a IncompatibleClassChangeError crash with EMF when loading forge with unrelated mod dependency issues
- fixed a model creation crash
- improved non animated horse and ravager head model positioning, fixed horse saddle positioning, horse chests still broken
- added translations fr_fr, de_de, & el_gr


0.2.5

- fixed random models only changing the animations correctly not the models themselves
- fixed head_yaw not constraining to 360 degrees in some cases *(the cause of some weirdness in my animated player model)*
- added code to redirect modded entities for their own jem loading to be implemented in future *(this should fix crashes caused by modded mobs like "player_companions:pig" as emf used to think it was a regular pig )*
- fixed mod compatibility flags to prevent users loading EMF alongside OptiFabric or CEM as this is not supported





