import { test, expect } from '@playwright/test';

test('test', async ({ page }) => {
  await page.goto('https://www.joinnus.com/events/sports/lima-liga-peruana-de-voley-betsson-72581');
  await expect(page.getByRole('heading', { name: 'Uso de cookies' })).toBeVisible();

  await page.getByRole('button', { name: 'Permitir todas' }).click();
  await expect(page.getByRole('button', { name: 'Leer más' })).toBeVisible();

  await page.getByText(/Dom 05 Abr/).click();
  await expect(page.getByText('No hay entradas disponibles')).toBeVisible({ timeout: 5000 });
});
