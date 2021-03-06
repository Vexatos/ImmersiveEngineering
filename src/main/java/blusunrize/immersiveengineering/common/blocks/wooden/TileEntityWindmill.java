package blusunrize.immersiveengineering.common.blocks.wooden;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraftforge.common.util.ForgeDirection;
import blusunrize.immersiveengineering.common.blocks.TileEntityIEBase;
import blusunrize.immersiveengineering.common.blocks.metal.TileEntityDynamo;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class TileEntityWindmill extends TileEntityIEBase
{
	public int facing = 2;
	public float rotation=0;
	public float turnSpeed=0;

	boolean canTurn = false;

	@Override
	public void updateEntity()
	{
		if(worldObj.getTotalWorldTime()%100==0)
			canTurn = checkArea();
		if(!canTurn)
			return;

		double mod = .00005;
		if(!worldObj.isRaining())
			mod *= .75;
		if(!worldObj.isThundering())
			mod *= .66;
		if(yCoord>200)
			mod *= 2;
		else if(yCoord>150)
			mod *= 1.5;
		else if(yCoord>100)
			mod *= 1.25;
		else if(yCoord<70)
			mod *= .33;
		mod*=getSpeedModifier();
		rotation += turnSpeed*mod;
		rotation %= 1;

		if(!worldObj.isRemote)
		{
			ForgeDirection fd = ForgeDirection.getOrientation(facing);
			if(worldObj.getTileEntity(xCoord-fd.offsetX,yCoord-fd.offsetY,zCoord-fd.offsetZ) instanceof TileEntityDynamo)
			{
				TileEntityDynamo dynamo = (TileEntityDynamo)worldObj.getTileEntity(xCoord-fd.offsetX,yCoord-fd.offsetY,zCoord-fd.offsetZ);
				if((facing==2||facing==3)&&dynamo.facing!=2&&dynamo.facing!=3)
					return;
				else if((facing==4||facing==5)&&dynamo.facing!=4&&dynamo.facing!=5)
					return;
				double power = turnSpeed*mod * 400;
				dynamo.inputRotation(Math.abs(power));
			}
		}
	}
	protected float getSpeedModifier()
	{
		return .33f;
	}

	public boolean checkArea()
	{
		turnSpeed=0;
		for(int hh=-6;hh<=6;hh++)
		{
			int r=Math.abs(hh)==6?1: Math.abs(hh)==5?3: Math.abs(hh)==4?4: Math.abs(hh)>1?5: 6;
			for(int ww=-r;ww<=r;ww++)
				if((hh!=0||ww!=0)&&!worldObj.isAirBlock(xCoord+(facing<=3?ww:0), yCoord+hh, zCoord+(facing<=3?0:ww)))
					return false;
		}

		for(int dd=1;dd<8;dd++)
		{
			int blocked = 0;
			for(int hh=-6;hh<=6;hh++)
			{
				int r=Math.abs(hh)==6?1: Math.abs(hh)==5?3: Math.abs(hh)==4?4: Math.abs(hh)>1?5: 6;
				for(int ww=-r;ww<=r;ww++)
				{
					int xx = xCoord+(facing<=3?ww:0)+(facing==4?-dd: facing==5?dd: 0);
					int yy = yCoord+hh;
					int zz = zCoord+(facing<=3?0:ww)+(facing==2?-dd: facing==3?dd: 0);
					if(worldObj.isAirBlock(xx,yy,zz))
						turnSpeed ++;
					else
						blocked++;
				}
			}
			if(blocked>100)
				return false;
			else if(blocked>50)
				return true;
		}

		return true;
	}

	@Override
	public void readCustomNBT(NBTTagCompound nbt)
	{
		facing = nbt.getInteger("facing");
		rotation = nbt.getFloat("rotation");
		turnSpeed = nbt.getFloat("turnSpeed");
	}
	@Override
	public void writeCustomNBT(NBTTagCompound nbt)
	{
		nbt.setInteger("facing", facing);
		nbt.setFloat("rotation", rotation);
		nbt.setFloat("turnSpeed", turnSpeed);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public AxisAlignedBB getRenderBoundingBox()
	{
		return AxisAlignedBB.getBoundingBox(xCoord-(facing<=3?6:0),yCoord-6,zCoord-(facing<=3?0:6), xCoord+(facing<=3?7:0),yCoord+7,zCoord+(facing<=3?0:7));
	}
}