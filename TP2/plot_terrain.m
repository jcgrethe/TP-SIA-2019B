function handler = plot_terrain(terrain_data)
	clf;
	colormap ("jet");
	
	x = terrain_data(:,1);
	y = terrain_data(:,2);
	z = terrain_data(:,3);

	x_linspace=linspace(min(x),max(x),30);
	y_linspace=linspace(min(y),max(y),30);

	[xx yy]=meshgrid(x_linspace,y_linspace);
	zz = griddata(x,y,z,xx,yy);

	handler = surfc(xx,yy,zz);
endfunction