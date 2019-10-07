function normalized = normalize(array, range, min, max)
  normalized = (array - min) / (max - min);
  range2 = range(2) - range(1);
  normalized = (normalized*range2) + range(1);
endfunction
