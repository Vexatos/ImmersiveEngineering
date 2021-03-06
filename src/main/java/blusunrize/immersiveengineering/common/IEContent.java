package blusunrize.immersiveengineering.common;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import blusunrize.immersiveengineering.ImmersiveEngineering;
import blusunrize.immersiveengineering.api.BlastFurnaceRecipe;
import blusunrize.immersiveengineering.api.CokeOvenRecipe;
import blusunrize.immersiveengineering.api.CrusherRecipe;
import blusunrize.immersiveengineering.api.DieselHandler;
import blusunrize.immersiveengineering.api.MultiblockHandler;
import blusunrize.immersiveengineering.common.blocks.BlockIEBase;
import blusunrize.immersiveengineering.common.blocks.BlockIEBase.BlockIESimple;
import blusunrize.immersiveengineering.common.blocks.BlockStorage;
import blusunrize.immersiveengineering.common.blocks.ItemBlockIEBase;
import blusunrize.immersiveengineering.common.blocks.metal.BlockMetalDecoration;
import blusunrize.immersiveengineering.common.blocks.metal.BlockMetalDevices;
import blusunrize.immersiveengineering.common.blocks.metal.BlockMetalMultiblocks;
import blusunrize.immersiveengineering.common.blocks.metal.TileEntityCapacitorHV;
import blusunrize.immersiveengineering.common.blocks.metal.TileEntityCapacitorLV;
import blusunrize.immersiveengineering.common.blocks.metal.TileEntityCapacitorMV;
import blusunrize.immersiveengineering.common.blocks.metal.TileEntityConnectorHV;
import blusunrize.immersiveengineering.common.blocks.metal.TileEntityConnectorLV;
import blusunrize.immersiveengineering.common.blocks.metal.TileEntityConnectorMV;
import blusunrize.immersiveengineering.common.blocks.metal.TileEntityConveyorBelt;
import blusunrize.immersiveengineering.common.blocks.metal.TileEntityCrusher;
import blusunrize.immersiveengineering.common.blocks.metal.TileEntityDieselGenerator;
import blusunrize.immersiveengineering.common.blocks.metal.TileEntityDynamo;
import blusunrize.immersiveengineering.common.blocks.metal.TileEntityFermenter;
import blusunrize.immersiveengineering.common.blocks.metal.TileEntityLightningRod;
import blusunrize.immersiveengineering.common.blocks.metal.TileEntityRefinery;
import blusunrize.immersiveengineering.common.blocks.metal.TileEntityRelayHV;
import blusunrize.immersiveengineering.common.blocks.metal.TileEntitySqueezer;
import blusunrize.immersiveengineering.common.blocks.metal.TileEntityStructuralArm;
import blusunrize.immersiveengineering.common.blocks.metal.TileEntityThermoelectricGen;
import blusunrize.immersiveengineering.common.blocks.metal.TileEntityTransformer;
import blusunrize.immersiveengineering.common.blocks.metal.TileEntityTransformerHV;
import blusunrize.immersiveengineering.common.blocks.multiblocks.MultiblockBlastFurnace;
import blusunrize.immersiveengineering.common.blocks.multiblocks.MultiblockCokeOven;
import blusunrize.immersiveengineering.common.blocks.multiblocks.MultiblockCrusher;
import blusunrize.immersiveengineering.common.blocks.multiblocks.MultiblockDieselGenerator;
import blusunrize.immersiveengineering.common.blocks.multiblocks.MultiblockFermenter;
import blusunrize.immersiveengineering.common.blocks.multiblocks.MultiblockRefinery;
import blusunrize.immersiveengineering.common.blocks.multiblocks.MultiblockSqueezer;
import blusunrize.immersiveengineering.common.blocks.plant.BlockIECrop;
import blusunrize.immersiveengineering.common.blocks.stone.BlockStoneDevices;
import blusunrize.immersiveengineering.common.blocks.stone.TileEntityBlastFurnace;
import blusunrize.immersiveengineering.common.blocks.stone.TileEntityCokeOven;
import blusunrize.immersiveengineering.common.blocks.wooden.BlockIEWoodenStairs;
import blusunrize.immersiveengineering.common.blocks.wooden.BlockWoodenDecoration;
import blusunrize.immersiveengineering.common.blocks.wooden.BlockWoodenDevices;
import blusunrize.immersiveengineering.common.blocks.wooden.TileEntityWatermill;
import blusunrize.immersiveengineering.common.blocks.wooden.TileEntityWindmill;
import blusunrize.immersiveengineering.common.blocks.wooden.TileEntityWindmillAdvanced;
import blusunrize.immersiveengineering.common.blocks.wooden.TileEntityWoodenCrate;
import blusunrize.immersiveengineering.common.blocks.wooden.TileEntityWoodenPost;
import blusunrize.immersiveengineering.common.crafting.IEFuelHandler;
import blusunrize.immersiveengineering.common.crafting.RecipeOreCrushing;
import blusunrize.immersiveengineering.common.entities.EntityRevolvershot;
import blusunrize.immersiveengineering.common.items.ItemBullet;
import blusunrize.immersiveengineering.common.items.ItemIEBase;
import blusunrize.immersiveengineering.common.items.ItemIESeed;
import blusunrize.immersiveengineering.common.items.ItemIETool;
import blusunrize.immersiveengineering.common.items.ItemRevolver;
import blusunrize.immersiveengineering.common.items.ItemWireCoil;
import blusunrize.immersiveengineering.common.util.Utils;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;

public class IEContent
{
	public static BlockIEBase blockOres;
	public static BlockIEBase blockStorage;
	public static BlockIEBase blockMetalDevice;
	public static BlockIEBase blockMetalDecoration;
	public static BlockIEBase blockMetalMultiblocks;
	public static BlockIEBase blockWoodenDevice;
	public static BlockIEBase blockWoodenDecoration;
	public static Block blockWoodenStair;
	public static BlockIEBase blockStoneDevice;
	public static Block blockCrop;
	public static ItemIEBase itemMetal;
	public static ItemIEBase itemMaterial;
	public static ItemIEBase itemSeeds;
	public static ItemIEBase itemWireCoil;
	public static ItemIEBase itemTool;
	public static ItemIEBase itemRevolver;
	public static ItemIEBase itemBullet;
	public static ItemIEBase itemFluidContainers;
	public static Fluid fluidCreosote;
	public static boolean IECreosote=false;
	public static Fluid fluidPlantoil;
	public static boolean IEPlantoil=false;
	public static Fluid fluidEthanol;
	public static boolean IEEthanol=false;
	public static Fluid fluidBiodiesel;
	public static boolean IEBiodiesel=false;

	public static void preInit()
	{
		blockOres = (BlockIEBase) new BlockIESimple("ore",Material.rock,ItemBlockIEBase.class, "Copper","Aluminum","Lead","Silver","Nickel").setHardness(3f).setResistance(5f);
		blockStorage = (BlockIEBase) new BlockStorage("Copper","Aluminum","Lead","Silver","Nickel","Constantan","Electrum","Steel", "CoilCopper","CoilElectrum","CoilHV").setHardness(4f).setResistance(5f);
		blockMetalDevice = new BlockMetalDevices();
		blockMetalDecoration = new BlockMetalDecoration();
		blockMetalMultiblocks = new BlockMetalMultiblocks();
		blockWoodenDevice = new BlockWoodenDevices();
		blockWoodenDecoration = new BlockWoodenDecoration();
		blockWoodenStair = new BlockIEWoodenStairs();
		blockStoneDevice = new BlockStoneDevices();
		blockCrop = new BlockIECrop("hemp", "0B","1B","2B","3B","4B","0T");

		itemMetal = new ItemIEBase("metal", 64, "ingotCopper","ingotAluminum","ingotLead","ingotSilver","ingotNickel","ingotConstantan","ingotElectrum","ingotSteel",   "dustIron","dustGold","dustCopper","dustAluminum","dustLead","dustSilver","dustNickel","dustConstantan","dustElectrum");
		itemMaterial = new ItemIEBase("material", 64, "treatedStick","waterwheelSegment","windmillBlade","hempFiber","fabric","windmillBladeAdvanced","coalCoke","gunpartBarrel","gunpartDrum","gunpartGrip","gunpartHammer","componentIron","componentSteel");
		itemSeeds = new ItemIESeed(blockCrop,"hemp");
		MinecraftForge.addGrassSeed(new ItemStack(itemSeeds), 5);
		itemWireCoil = new ItemWireCoil();
		itemTool = new ItemIETool();
		itemRevolver = new ItemRevolver();
		itemBullet = new ItemBullet();
		itemFluidContainers = new ItemIEBase("fluidContainers", 64, "bottleCreosote","bucketCreosote",  "bottlePlantoil","bucketPlantoil",  "bottleEthanol","bucketEthanol", "bottleBiodiesel","bucketBiodiesel");


		fluidCreosote = FluidRegistry.getFluid("creosote");
		if(fluidCreosote==null)
		{
			fluidCreosote = new Fluid("creosote").setDensity(800).setViscosity(3000);
			FluidRegistry.registerFluid(fluidCreosote);
			IECreosote=true;
		}
		fluidPlantoil = FluidRegistry.getFluid("plantoil");
		if(fluidPlantoil==null)
		{
			fluidPlantoil = new Fluid("plantoil").setDensity(925).setViscosity(2000);
			FluidRegistry.registerFluid(fluidPlantoil);
			IEPlantoil=true;
		}
		fluidEthanol = FluidRegistry.getFluid("ethanol");
		if(fluidEthanol==null)
		{
			fluidEthanol = new Fluid("ethanol").setDensity(789).setViscosity(1000);
			FluidRegistry.registerFluid(fluidEthanol);
			IEEthanol=true;
		}
		fluidBiodiesel = FluidRegistry.getFluid("biodiesel");
		if(fluidBiodiesel==null)
		{
			fluidBiodiesel = new Fluid("biodiesel").setDensity(789).setViscosity(1000);
			FluidRegistry.registerFluid(fluidBiodiesel);
			IEBiodiesel=true;
		}

		//Ore Dict
		registerToOreDict("ore", blockOres);
		registerToOreDict("block", blockStorage, 0,1,2,3,4,5,6,7);
		registerToOreDict("", itemMetal);
		registerOre("Cupronickel",	null,new ItemStack(itemMetal,1,5),new ItemStack(itemMetal,1,15),new ItemStack(blockStorage,1,5));

		OreDictionary.registerOre("treatedStick", new ItemStack(itemMaterial,1,0));
		OreDictionary.registerOre("fuelCoke", new ItemStack(itemMaterial,1,6));
		OreDictionary.registerOre("blockFuelCoke", new ItemStack(blockStoneDevice,1,3));
		//Vanilla OreDict
		OreDictionary.registerOre("bricksStone", new ItemStack(Blocks.stonebrick));
		//Fluid Containers
		FluidContainerRegistry.registerFluidContainer(fluidCreosote, new ItemStack(itemFluidContainers,1,0), new ItemStack(Items.glass_bottle));
		FluidContainerRegistry.registerFluidContainer(fluidCreosote, new ItemStack(itemFluidContainers,1,1), new ItemStack(Items.bucket));
		FluidContainerRegistry.registerFluidContainer(fluidPlantoil, new ItemStack(itemFluidContainers,1,2), new ItemStack(Items.glass_bottle));
		FluidContainerRegistry.registerFluidContainer(fluidPlantoil, new ItemStack(itemFluidContainers,1,3), new ItemStack(Items.bucket));
		FluidContainerRegistry.registerFluidContainer(fluidEthanol, new ItemStack(itemFluidContainers,1,4), new ItemStack(Items.glass_bottle));
		FluidContainerRegistry.registerFluidContainer(fluidEthanol, new ItemStack(itemFluidContainers,1,5), new ItemStack(Items.bucket));
		FluidContainerRegistry.registerFluidContainer(fluidBiodiesel, new ItemStack(itemFluidContainers,1,6), new ItemStack(Items.glass_bottle));
		FluidContainerRegistry.registerFluidContainer(fluidBiodiesel, new ItemStack(itemFluidContainers,1,7), new ItemStack(Items.bucket));
		//Mining
		blockOres.setHarvestLevel("pickaxe", 1, 0);//Copper
		blockOres.setHarvestLevel("pickaxe", 1, 1);//Bauxite
		blockOres.setHarvestLevel("pickaxe", 2, 2);//Lead
		blockOres.setHarvestLevel("pickaxe", 2, 3);//Silver
		blockOres.setHarvestLevel("pickaxe", 2, 4);//Nickel
		blockStorage.setHarvestLevel("pickaxe", 1, 0);//Copper
		blockStorage.setHarvestLevel("pickaxe", 1, 1);//Aluminium
		blockStorage.setHarvestLevel("pickaxe", 2, 2);//Lead
		blockStorage.setHarvestLevel("pickaxe", 2, 3);//Silver
		blockStorage.setHarvestLevel("pickaxe", 2, 4);//Nickel
		blockStorage.setHarvestLevel("pickaxe", 2, 5);//Constantan
		blockStorage.setHarvestLevel("pickaxe", 2, 6);//Electrum
		blockStorage.setHarvestLevel("pickaxe", 2, 7);//Steel
		blockStorage.setHarvestLevel("pickaxe", 2, 8);//CoilCopper
		blockStorage.setHarvestLevel("pickaxe", 2, 9);//CoilElectrum
		blockStorage.setHarvestLevel("pickaxe", 2,10);//CoilHV


		addConfiguredWorldgen(blockOres,0, "ore_copper");
		addConfiguredWorldgen(blockOres,1, "ore_bauxite");
		addConfiguredWorldgen(blockOres,2, "ore_lead");
		addConfiguredWorldgen(blockOres,3, "ore_silver");
		addConfiguredWorldgen(blockOres,4, "ore_nickel");
	}

	public static void init()
	{
		/**TILEENTITIES*/
		registerTile(TileEntityWoodenPost.class);
		registerTile(TileEntityWatermill.class);
		registerTile(TileEntityWindmill.class);
		registerTile(TileEntityWindmillAdvanced.class);
		registerTile(TileEntityWoodenCrate.class);

		registerTile(TileEntityConnectorLV.class);
		registerTile(TileEntityCapacitorLV.class);
		registerTile(TileEntityConnectorMV.class);
		registerTile(TileEntityCapacitorMV.class);
		registerTile(TileEntityTransformer.class);
		registerTile(TileEntityRelayHV.class);
		registerTile(TileEntityConnectorHV.class);
		registerTile(TileEntityCapacitorHV.class);
		registerTile(TileEntityTransformerHV.class);
		registerTile(TileEntityDynamo.class);
		registerTile(TileEntityThermoelectricGen.class);
		registerTile(TileEntityConveyorBelt.class);

		registerTile(TileEntityLightningRod.class);
		registerTile(TileEntityDieselGenerator.class);
		registerTile(TileEntitySqueezer.class);
		registerTile(TileEntityFermenter.class);
		registerTile(TileEntityRefinery.class);
		registerTile(TileEntityCrusher.class);

		registerTile(TileEntityStructuralArm.class);


		registerTile(TileEntityCokeOven.class);
		registerTile(TileEntityBlastFurnace.class);

		/**ENTITIES*/
		EntityRegistry.registerModEntity(EntityRevolvershot.class, "revolverShot", 0, ImmersiveEngineering.instance, 64, 1, true);


		/**SMELTING*/
		//Ores
		FurnaceRecipes.smelting().func_151394_a(new ItemStack(blockOres,1,0), new ItemStack(itemMetal,1,0), 0.3F);
		FurnaceRecipes.smelting().func_151394_a(new ItemStack(blockOres,1,1), new ItemStack(itemMetal,1,1), 0.3F);
		FurnaceRecipes.smelting().func_151394_a(new ItemStack(blockOres,1,2), new ItemStack(itemMetal,1,2), 0.7F);
		FurnaceRecipes.smelting().func_151394_a(new ItemStack(blockOres,1,3), new ItemStack(itemMetal,1,3), 1.0F);
		FurnaceRecipes.smelting().func_151394_a(new ItemStack(blockOres,1,4), new ItemStack(itemMetal,1,4), 1.0F);
		//Dusts
		FurnaceRecipes.smelting().func_151394_a(new ItemStack(itemMetal,1,8), new ItemStack(Items.iron_ingot), 0.7F);
		FurnaceRecipes.smelting().func_151394_a(new ItemStack(itemMetal,1,9), new ItemStack(Items.gold_ingot), 1.0F);
		FurnaceRecipes.smelting().func_151394_a(new ItemStack(itemMetal,1,10), new ItemStack(itemMetal,1,0), 0.3F);
		FurnaceRecipes.smelting().func_151394_a(new ItemStack(itemMetal,1,11), new ItemStack(itemMetal,1,1), 0.3F);
		FurnaceRecipes.smelting().func_151394_a(new ItemStack(itemMetal,1,12), new ItemStack(itemMetal,1,2), 0.7F);
		FurnaceRecipes.smelting().func_151394_a(new ItemStack(itemMetal,1,13), new ItemStack(itemMetal,1,3), 1.0F);
		FurnaceRecipes.smelting().func_151394_a(new ItemStack(itemMetal,1,14), new ItemStack(itemMetal,1,4), 0.5F);
		FurnaceRecipes.smelting().func_151394_a(new ItemStack(itemMetal,1,15), new ItemStack(itemMetal,1,5), 0.5F);


		/**CRAFTING*/
		ItemStack treatedWood = new ItemStack(blockWoodenDecoration,1,0);
		ItemStack copperCoil = new ItemStack(blockStorage,1,8);
		ItemStack electrumCoil = new ItemStack(blockStorage,1,9);
		ItemStack hvCoil = new ItemStack(blockStorage,1,10);
		ItemStack componentIron = new ItemStack(itemMaterial,1,11);
		ItemStack componentSteel = new ItemStack(itemMaterial,1,12);

		addOredictRecipe(new ItemStack(itemTool,1,0), " IF"," SI","S  ", 'I',"ingotIron", 'S',"stickWood", 'F',new ItemStack(Items.string));
		addOredictRecipe(new ItemStack(itemTool,1,1), "SI"," S", 'I',"ingotIron", 'S',"treatedStick").setMirrored(true);
		addOredictRecipe(new ItemStack(itemTool,1,2), " P ","SCS", 'C',"ingotCopper", 'P',Items.compass, 'S',"treatedStick");
		addShapelessOredictRecipe(new ItemStack(itemTool,1,3), Items.book,Blocks.lever);
		addOredictRecipe(new ItemStack(itemRevolver,1,0), " I ","HDB","GIG", 'I',"ingotIron",'B',new ItemStack(itemMaterial,1,7),'D',new ItemStack(itemMaterial,1,8),'G',new ItemStack(itemMaterial,1,9),'H',new ItemStack(itemMaterial,1,10));
		addOredictRecipe(new ItemStack(itemRevolver,1,2), "  I","IIS","  I", 'I',"ingotIron",'S',"ingotSteel");

		addOredictRecipe(new ItemStack(itemBullet,3,0), "I I","I I"," I ", 'I',"ingotCopper");
		addOredictRecipe(new ItemStack(itemBullet,3,1), "PDP","PDP"," I ", 'I',"ingotCopper",'P',Items.paper,'D',"dyeRed");
		addBulletRecipes(2, "ingotIron", 0);
		addBulletRecipes(3, "ingotSteel", 0);
		if(!OreDictionary.getOres("ingotTungsten").isEmpty())
			addBulletRecipes(3, "ingotTungsten", 0);
		if(!OreDictionary.getOres("ingotCyanite").isEmpty())
			addBulletRecipes(3, "ingotCyanite", 0);
		addBulletRecipes(4, "dustIron", 1);
		addBulletRecipes(5, Blocks.tnt, 0);
		addBulletRecipes(6, "dustAluminum", 1);

		addShapelessOredictRecipe(new ItemStack(itemMetal,2,15), "dustCopper","dustNickel");
		addShapelessOredictRecipe(new ItemStack(itemMetal,2,16), "dustSilver","dustGold");

		addOredictRecipe(new ItemStack(itemMaterial,4,0), "W","W", 'W',treatedWood);
		addOredictRecipe(new ItemStack(itemMaterial,1,1), " S ","SBS","BSB", 'B',treatedWood, 'S',"treatedStick");
		addOredictRecipe(new ItemStack(itemMaterial,1,2), "BB ","SSB","SS ", 'B',treatedWood, 'S',"treatedStick");
		addOredictRecipe(new ItemStack(itemMaterial,1,4), "HHH","HSH","HHH", 'H',new ItemStack(itemMaterial,1,3), 'S',"stickWood");
		addShapelessOredictRecipe(new ItemStack(itemMaterial,1,5), new ItemStack(itemMaterial,1,2),new ItemStack(itemMaterial,1,4),new ItemStack(itemMaterial,1,4),new ItemStack(itemMaterial,1,4),new ItemStack(itemMaterial,1,4));
		addShapelessOredictRecipe(new ItemStack(itemMaterial,1,6), new ItemStack(blockStoneDevice,1,3));
		addOredictRecipe(new ItemStack(itemMaterial,1,7), "III", 'I',"ingotSteel");
		addOredictRecipe(new ItemStack(itemMaterial,1,8), " I ","III"," I ", 'I',"ingotSteel");
		addOredictRecipe(new ItemStack(itemMaterial,1,9), "SS","IS","SS", 'I',"ingotCopper",'S',"treatedStick");
		addOredictRecipe(new ItemStack(itemMaterial,1,10), "I  ","II "," II", 'I',"ingotSteel");
		addOredictRecipe(new ItemStack(itemMaterial,1,10), "I  ","II "," II", 'I',"ingotSteel");
		addOredictRecipe(componentIron, " I ","ICI"," I ", 'I',"ingotIron",'C',"ingotCopper");
		addOredictRecipe(componentSteel, " I ","ICI"," I ", 'I',"ingotSteel",'C',"ingotCopper");

		addOredictRecipe(new ItemStack(itemWireCoil,4,0), " I ","ISI"," I ", 'I',"ingotCopper", 'S',"stickWood");
		addOredictRecipe(new ItemStack(itemWireCoil,4,1), " I ","ISI"," I ", 'I',"ingotElectrum", 'S',"stickWood");
		addOredictRecipe(new ItemStack(itemWireCoil,4,2), " I ","ASA"," I ", 'I',"ingotSteel", 'A',"ingotAluminum", 'S',"stickWood");

		for (ItemStack container : Utils.getContainersFilledWith(new FluidStack(fluidCreosote,1000)))
			addOredictRecipe(new ItemStack(blockWoodenDecoration,8,0), "WWW","WCW","WWW", 'W',"plankWood",'C',container);
		addOredictRecipe(new ItemStack(blockWoodenDecoration,2,1), "SSS","SSS", 'S',"treatedStick");
		addOredictRecipe(new ItemStack(blockWoodenDecoration,6,2), "WWW", 'W',treatedWood);
		addOredictRecipe(new ItemStack(blockWoodenDecoration,6,5), "WWW"," S ","S S", 'W',treatedWood,'S',new ItemStack(blockWoodenDecoration,1,1));
		addOredictRecipe(new ItemStack(blockWoodenStair,4,0), "  W"," WW","WWW", 'W',treatedWood).setMirrored(true);

		addOredictRecipe(new ItemStack(blockWoodenDevice,1,0), "F","F","S", 'F',new ItemStack(blockWoodenDecoration,1,1),'S',"bricksStone");
		addOredictRecipe(new ItemStack(blockWoodenDevice,1,1), " P ","PWP"," P ", 'P',new ItemStack(itemMaterial,1,1),'W',treatedWood);
		addOredictRecipe(new ItemStack(blockWoodenDevice,1,2), " P ","PIP"," P ", 'P',new ItemStack(itemMaterial,1,2),'I',"ingotIron");
		addOredictRecipe(new ItemStack(blockWoodenDevice,1,3), "PPP","PIP","PPP", 'P',new ItemStack(itemMaterial,1,5),'I',"ingotSteel");
		addOredictRecipe(new ItemStack(blockWoodenDevice,1,4), "WWW","W W","WWW", 'W',treatedWood);

		addOredictRecipe(new ItemStack(blockStoneDevice,6,0), "CCC","HHH","CCC", 'C',Blocks.clay,'H',new ItemStack(itemMaterial,1,3));
		addOredictRecipe(new ItemStack(blockStoneDevice,2,1), "CBC","BSB","CBC", 'S',"sandstone",'C',Items.clay_ball,'B',"ingotBrick");
		addOredictRecipe(new ItemStack(blockStoneDevice,2,2), "NBN","BDB","NBN", 'D',Items.blaze_powder,'N',"ingotBrickNether",'B',"ingotBrick");
		addOredictRecipe(new ItemStack(blockStoneDevice,1,3), "CCC","CCC","CCC", 'C',new ItemStack(itemMaterial,1,6));
		addOredictRecipe(new ItemStack(blockStoneDevice,2,4), " I ","GDG"," I ", 'G',"blockGlass",'I',"dustIron",'D',"dyeGreen");
		addOredictRecipe(new ItemStack(blockStoneDevice,2,4), " G ","IDI"," G ", 'G',"blockGlass",'I',"dustIron",'D',"dyeGreen");

		addOredictRecipe(new ItemStack(blockStorage,1,0), "III","III","III", 'I',"ingotCopper");
		addOredictRecipe(new ItemStack(blockStorage,1,1), "III","III","III", 'I',"ingotAluminum");
		addOredictRecipe(new ItemStack(blockStorage,1,2), "III","III","III", 'I',"ingotLead");
		addOredictRecipe(new ItemStack(blockStorage,1,3), "III","III","III", 'I',"ingotSilver");
		addOredictRecipe(new ItemStack(blockStorage,1,4), "III","III","III", 'I',"ingotNickel");
		addOredictRecipe(new ItemStack(blockStorage,1,5), "III","III","III", 'I',"ingotConstantan");
		addOredictRecipe(new ItemStack(blockStorage,1,6), "III","III","III", 'I',"ingotElectrum");
		addOredictRecipe(new ItemStack(blockStorage,1,7), "III","III","III", 'I',"ingotSteel");
		addOredictRecipe(new ItemStack(blockStorage,1,8), "WWW","WIW","WWW", 'W',new ItemStack(itemWireCoil,1,0),'I',"ingotIron");
		addOredictRecipe(new ItemStack(blockStorage,1,9), "WWW","WIW","WWW", 'W',new ItemStack(itemWireCoil,1,1),'I',"ingotIron");
		addOredictRecipe(new ItemStack(blockStorage,1,10), "WWW","WIW","WWW", 'W',new ItemStack(itemWireCoil,1,2),'I',"ingotIron");

		addOredictRecipe(new ItemStack(blockMetalDevice,8, BlockMetalDevices.META_connectorLV), "BIB"," I ","BIB", 'I',"ingotCopper",'B',Blocks.hardened_clay);
		addOredictRecipe(new ItemStack(blockMetalDevice,1, BlockMetalDevices.META_capacitorLV), "III","CLC","WRW", 'L',"ingotLead",'I',"ingotIron",'C',"ingotCopper",'R',"dustRedstone",'W',treatedWood);
		addOredictRecipe(new ItemStack(blockMetalDevice,8, BlockMetalDevices.META_connectorMV), "BIB"," I ","BIB", 'I',"ingotIron",'B',Blocks.hardened_clay);
		addOredictRecipe(new ItemStack(blockMetalDevice,1, BlockMetalDevices.META_capacitorMV), "III","ELE","WRW", 'L',"ingotLead",'I',"ingotIron",'E',"ingotElectrum",'R',"blockRedstone",'W',treatedWood);
		addOredictRecipe(new ItemStack(blockMetalDevice,1, BlockMetalDevices.META_transformer), "L M","IBI","III", 'L',new ItemStack(blockMetalDevice,1,BlockMetalDevices.META_connectorLV),'M',new ItemStack(blockMetalDevice,1,BlockMetalDevices.META_connectorMV),'I',"ingotIron",'B',electrumCoil).setMirrored(true);
		addOredictRecipe(new ItemStack(blockMetalDevice,8, BlockMetalDevices.META_relayHV), "BIB"," I ","BIB", 'I',"ingotIron",'B',new ItemStack(blockStoneDevice,1,4));
		addOredictRecipe(new ItemStack(blockMetalDevice,4, BlockMetalDevices.META_connectorHV), "BIB","BIB","BIB", 'I',"ingotAluminum",'B',Blocks.hardened_clay);
		addOredictRecipe(new ItemStack(blockMetalDevice,1, BlockMetalDevices.META_capacitorHV), "III","ALA","WRW", 'L',"blockLead",'I',"ingotSteel",'A',"ingotAluminum",'R',"blockRedstone",'W',treatedWood);
		addOredictRecipe(new ItemStack(blockMetalDevice,1, BlockMetalDevices.META_transformerHV), "M H","IBI","III", 'H',new ItemStack(blockMetalDevice,1,BlockMetalDevices.META_connectorHV),'M',new ItemStack(blockMetalDevice,1,BlockMetalDevices.META_connectorMV),'I',"ingotIron",'B',hvCoil).setMirrored(true);
		addOredictRecipe(new ItemStack(blockMetalDevice,1, BlockMetalDevices.META_dynamo), "RCR","III", 'C',copperCoil,'I',"ingotIron",'R',"dustRedstone");
		addOredictRecipe(new ItemStack(blockMetalDevice,1, BlockMetalDevices.META_thermoelectricGen), "III","CBC","CCC", 'I',"ingotSteel",'C',"ingotConstantan",'B',copperCoil);
		addOredictRecipe(new ItemStack(blockMetalDevice,8, BlockMetalDevices.META_conveyorBelt), "LLL","IRI", 'I',"ingotIron",'R',"dustRedstone",'L',Items.leather);

		addOredictRecipe(new ItemStack(blockMetalDecoration,16,BlockMetalDecoration.META_fence), "III","III", 'I',"ingotSteel");
		addOredictRecipe(new ItemStack(blockMetalDecoration, 6,BlockMetalDecoration.META_scaffolding), "III"," S ","S S", 'I',"ingotSteel",'S',new ItemStack(blockMetalDecoration,1,0));
		addOredictRecipe(new ItemStack(blockMetalDecoration, 4,BlockMetalDecoration.META_lantern), " I ","PGP"," I ", 'G',"glowstone",'I',"ingotIron",'P',"paneGlass");
		addOredictRecipe(new ItemStack(blockMetalDecoration, 4,BlockMetalDecoration.META_structuralArm), "B  ","BB ","BBB", 'B',new ItemStack(blockMetalDecoration,1,1));
		addOredictRecipe(new ItemStack(blockMetalDecoration, 2,BlockMetalDecoration.META_radiator), "ICI","CBC","ICI", 'I',"ingotSteel",'C',"ingotCopper",'B',Items.water_bucket);
		addOredictRecipe(new ItemStack(blockMetalDecoration, 2,BlockMetalDecoration.META_heavyEngineering), "IGI","PEP","IGI", 'I',"ingotSteel",'E',"ingotElectrum",'G',componentSteel,'P',Blocks.piston);
		addOredictRecipe(new ItemStack(blockMetalDecoration, 2,BlockMetalDecoration.META_generator), "III","EDE","III", 'I',"ingotSteel",'E',"ingotElectrum",'D',new ItemStack(blockMetalDevice,1, BlockMetalDevices.META_dynamo));
		addOredictRecipe(new ItemStack(blockMetalDecoration, 2,BlockMetalDecoration.META_lightEngineering), "IGI","CCC","IGI", 'I',"ingotIron",'C',"ingotCopper",'G',componentIron);

		addOredictRecipe(new ItemStack(blockMetalMultiblocks, 2,BlockMetalMultiblocks.META_squeezer), "IPI","GDG","IPI", 'I',"ingotIron",'D',"dyeGreen",'G',componentIron,'P',Blocks.piston);
		addOredictRecipe(new ItemStack(blockMetalMultiblocks, 2,BlockMetalMultiblocks.META_fermenter), "IPI","GDG","IPI", 'I',"ingotIron",'D',"dyeBlue",'G',componentIron,'P',Blocks.piston);

		CokeOvenRecipe.addRecipe(new ItemStack(itemMaterial,1,6), new ItemStack(Items.coal), 1800, 500);
		CokeOvenRecipe.addRecipe(new ItemStack(blockStoneDevice,1,1), "blockCoal", 1800*9, 5000);
		CokeOvenRecipe.addRecipe(new ItemStack(Items.coal,1,1), "logWood", 900, 250);
		BlastFurnaceRecipe.addRecipe(new ItemStack(itemMetal,1,7), "ingotIron", 1200);
		BlastFurnaceRecipe.addRecipe(new ItemStack(blockStorage,1,7), "blockIron", 1200*9);

		BlastFurnaceRecipe.addBlastFuel("fuelCoke", 3200);
		BlastFurnaceRecipe.addBlastFuel("blockFuelCoke", 3200*10);
		BlastFurnaceRecipe.addBlastFuel("charcoal", 1600);
		BlastFurnaceRecipe.addBlastFuel("blockCharcoal", 1600*10);
		GameRegistry.registerFuelHandler(new IEFuelHandler());

		addCrusherRecipe( 8, "Iron");
		addCrusherRecipe( 9, "Gold");
		addCrusherRecipe(10, "Copper");
		addCrusherRecipe(11, "Aluminum");
		addCrusherRecipe(12, "Lead");
		addCrusherRecipe(13, "Silver");
		addCrusherRecipe(14, "Nickel");
		addCrusherRecipe(15, "Constantan");
		addCrusherRecipe(16, "Electrum");
		CrusherRecipe.addRecipe(new ItemStack(Items.dye,9,4), "oreLapis", 4000);
		CrusherRecipe.addRecipe(new ItemStack(Items.diamond,2), "oreDiamond", 4000);
		CrusherRecipe.addRecipe(new ItemStack(Items.redstone,8), "oreRedstone", 4000);
		CrusherRecipe.addRecipe(new ItemStack(Items.emerald,2), "oreEmerald", 4000);
		CrusherRecipe.addRecipe(new ItemStack(Items.quartz,3), "oreQuartz", 4000);
		CrusherRecipe.addRecipe(new ItemStack(Items.coal,4), "oreCoal", 4000);
		CrusherRecipe.addRecipe(new ItemStack(Blocks.sand), "cobblestone", 3200);
		CrusherRecipe.addRecipe(new ItemStack(Blocks.sand), "blockGlass", 3200);
		CrusherRecipe.addRecipe(new ItemStack(Items.quartz,4), "blockQuartz", 3200);
		addOreDictCrusherRecipe("Tin");
		addOreDictCrusherRecipe("Bronze");
		addOreDictCrusherRecipe("Steel");
		addOreDictCrusherRecipe("Enderium");
		addOreDictCrusherRecipe("Lumium");
		addOreDictCrusherRecipe("Signalum");
		addOreDictCrusherRecipe("Invar");
		addOreDictCrusherRecipe("Mithril");
		addOreDictCrusherRecipe("Platinum");
		addItemToOreDictCrusherRecipe("dustCoal",1, new ItemStack(Items.coal), 2400);


		DieselHandler.registerFuel(fluidBiodiesel, 125);
		DieselHandler.registerFuel(FluidRegistry.getFluid("fuel"), 375);

		DieselHandler.registerPlantoilSource(Items.wheat_seeds, 80);
		DieselHandler.registerPlantoilSource(Items.pumpkin_seeds, 80);
		DieselHandler.registerPlantoilSource(Items.melon_seeds, 80);
		DieselHandler.registerPlantoilSource(itemSeeds, 80);

		DieselHandler.registerEthanolSource(Items.reeds, 80);

		MultiblockHandler.registerMultiblock(MultiblockCokeOven.instance);
		MultiblockHandler.registerMultiblock(MultiblockBlastFurnace.instance);
		MultiblockHandler.registerMultiblock(MultiblockDieselGenerator.instance);
		MultiblockHandler.registerMultiblock(MultiblockSqueezer.instance);
		MultiblockHandler.registerMultiblock(MultiblockFermenter.instance);
		MultiblockHandler.registerMultiblock(MultiblockRefinery.instance);
		MultiblockHandler.registerMultiblock(MultiblockCrusher.instance);

		//Railcraft Compat
		if(Loader.isModLoaded("Railcraft"))
		{
			Block rcCube = GameRegistry.findBlock("Railcraft", "cube");
			if(rcCube!=null)
				OreDictionary.registerOre("blockFuelCoke", new ItemStack(rcCube,1,0));
		}
	}

	public static void loadComplete()
	{
		//Crushing
		addHammerCrushingRecipe("Iron");
		addHammerCrushingRecipe("Gold");
		addHammerCrushingRecipe("Copper");
		addHammerCrushingRecipe("Aluminum");
		addHammerCrushingRecipe("Lead");
		addHammerCrushingRecipe("Silver");
		addHammerCrushingRecipe("Nickel");
		Config.setBoolean("crushingOreRecipe", validCrushingOres.isEmpty());
	}



	public static void registerToOreDict(String type, ItemIEBase item, int... metas)
	{
		if(metas==null||metas.length<1)
			for(int meta=0; meta<item.subNames.length; meta++)
				OreDictionary.registerOre(type+item.subNames[meta], new ItemStack(item,1,meta));
		else
			for(int meta: metas)
				OreDictionary.registerOre(type+item.subNames[meta], new ItemStack(item,1,meta));
	}
	public static void registerToOreDict(String type, BlockIEBase item, int... metas)
	{
		if(metas==null||metas.length<1)
			for(int meta=0; meta<item.subNames.length; meta++)
				OreDictionary.registerOre(type+item.subNames[meta], new ItemStack(item,1,meta));
		else
			for(int meta: metas)
				OreDictionary.registerOre(type+item.subNames[meta], new ItemStack(item,1,meta));
	}
	public static void registerOre(String type, ItemStack ore, ItemStack ingot, ItemStack dust, ItemStack block)
	{
		if(ore!=null)
			OreDictionary.registerOre("ore"+type, ore);
		if(ingot!=null)
			OreDictionary.registerOre("ingot"+type, ingot);
		if(dust!=null)
			OreDictionary.registerOre("dust"+type, dust);
		if(block!=null)
			OreDictionary.registerOre("block"+type, block);
	}

	public static void registerTile(Class<? extends TileEntity> tile)
	{
		String s = tile.getSimpleName();
		s = s.substring(s.indexOf("TileEntity")+"TileEntity".length());
		GameRegistry.registerTileEntity(tile, ImmersiveEngineering.MODID+":"+ s);
	}

	public static void addConfiguredWorldgen(Block block, int meta, String config)
	{
		int[] values = Config.getIntArray(config);
		if(values!=null && values.length>=5 && values[0]>0)
			IEWorldGen.addOreGen(block, meta, values[0],values[1],values[2], values[3],values[4]);
	}

	public static ShapedOreRecipe addOredictRecipe(ItemStack output, Object... recipe)
	{
		ShapedOreRecipe sor = new ShapedOreRecipe(output, recipe);
		GameRegistry.addRecipe(sor);
		return sor;
	}
	public static void addShapelessOredictRecipe(ItemStack output, Object... recipe)
	{
		GameRegistry.addRecipe(new ShapelessOreRecipe(output, recipe));
	}
	public static List<String> validCrushingOres = new ArrayList();
	public static void addHammerCrushingRecipe(String oreName)
	{
		if(OreDictionary.getOres("dust"+oreName).size()<2)
		{
			GameRegistry.addRecipe(new RecipeOreCrushing(oreName));
			validCrushingOres.add(oreName);
		}
	}
	public static void addBulletRecipes(int meta, Object load, int casingType)
	{
		addOredictRecipe(new ItemStack(itemBullet,3,meta), "III","CCC","GGG", 'I',load,'C',new ItemStack(itemBullet,1,casingType),'G',Items.gunpowder);
		addOredictRecipe(new ItemStack(itemBullet,2,meta), "II","CC","GG", 'I',load,'C',new ItemStack(itemBullet,1,casingType),'G',Items.gunpowder);
		addOredictRecipe(new ItemStack(itemBullet,1,meta), "I","C","G", 'I',load,'C',new ItemStack(itemBullet,1,casingType),'G',Items.gunpowder);
	}
	public static void addCrusherRecipe(int dustMeta, String ore)
	{
		if(!OreDictionary.getOres("ore"+ore).isEmpty())
			CrusherRecipe.addRecipe(new ItemStack(itemMetal,2,dustMeta), "ore"+ore, 4000);
		if(!OreDictionary.getOres("ingot"+ore).isEmpty())
			CrusherRecipe.addRecipe(new ItemStack(itemMetal,1,dustMeta), "ingot"+ore, 2400);
	}
	public static void addOreDictCrusherRecipe(String ore)
	{
		if(OreDictionary.getOres("dust"+ore).isEmpty())
			return;
		ItemStack dust = OreDictionary.getOres("dust"+ore).get(0);
		if(dust==null)
			return;
		if(!OreDictionary.getOres("ore"+ore).isEmpty())
			CrusherRecipe.addRecipe(Utils.copyStackWithAmount(dust, 2), "ore"+ore, 4000);
		if(!OreDictionary.getOres("ingot"+ore).isEmpty())
			CrusherRecipe.addRecipe(Utils.copyStackWithAmount(dust, 1), "ingot"+ore, 2400);
	}
	public static void addItemToOreDictCrusherRecipe(String oreName, int outSize, Object input, int energy)
	{
		if(OreDictionary.getOres(oreName).isEmpty())
			return;
		ItemStack out = OreDictionary.getOres(oreName).get(0);
		if(out==null)
			return;
		CrusherRecipe.addRecipe(Utils.copyStackWithAmount(out, outSize), input, energy);
	}

}