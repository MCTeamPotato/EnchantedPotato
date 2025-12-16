# 1.9.1
**DisableConfig and its related logic is removed.**

At 1.21.1 and above, unlike 1.20.1 and below, Enchantment is data-driven. 

While it's very easy and convenient to configurably disable any enchantments using Java in 1.20.1, I failed to find a fast way to handle those f**king jsons.

The disabling logic we are removing is actually hacky and somehow problematic. Generally it cannot integrate smoothly into Minecraft survivors' adventures.