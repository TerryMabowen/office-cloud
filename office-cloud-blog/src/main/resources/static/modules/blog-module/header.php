<!DOCTYPE html> 
<html class="no-js" <?php language_attributes(); ?>>

<head>
	<meta charset="<?php bloginfo('charset'); ?>">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link rel="profile" href="http://gmpg.org/xfn/11">
	<link rel="pingback" href="<?php bloginfo( 'pingback_url' ); ?>">
	
	<?php wp_head(); ?>
</head>

<body <?php body_class(); ?>>

<?php if ( function_exists( 'wp_body_open' ) ) { wp_body_open(); } ?>

<a class="skip-link screen-reader-text" href="#page"><?php _e( 'Skip to content', 'readline' ); ?></a>

<div id="wrapper">

	<div class="main">

		<header id="header">

			<?php if ( get_header_image() == '' ) : ?>
				<div id="header-top" class="group pad">
					<?php echo readline_site_title(); ?>
					<?php if ( display_header_text() == true ): ?>
						<p class="site-description"><?php bloginfo( 'description' ); ?></p>
					<?php endif; ?>
				</div>	
			<?php endif; ?>
			
			<?php if ( get_header_image() ) : ?>
				<div class="site-header">
					<a href="<?php echo esc_url( home_url('/') ); ?>" rel="home">
						<img class="site-image" src="<?php header_image(); ?>" alt="<?php echo esc_attr( get_bloginfo( 'name', 'display' ) ); ?>">
					</a>
				</div>
			<?php endif; ?>

			<div id="header-sticky">
				<?php if ( has_nav_menu('header') ): ?>
					<nav class="nav-container group" id="nav-header">
						<div class="nav-toggle"><i class="fas fa-bars"></i></div>
						<div class="nav-text"><!-- put your mobile menu text here --></div>
						<div class="nav-wrap container"><?php wp_nav_menu(array('theme_location'=>'header','menu_class'=>'nav container-inner group','container'=>'','menu_id' => '','fallback_cb'=> false)); ?></div>				
					</nav><!--/#nav-header-->
				<?php endif; ?>
			</div><!--/#header-sticky-->
			
			<?php if ( has_nav_menu('mobile') ): ?>
				<nav class="nav-container group" id="nav-mobile">
					<div class="nav-toggle"><i class="fas fa-bars"></i></div>
					<div class="nav-text"><!-- put your mobile menu text here --></div>
					<div class="nav-wrap container"><?php wp_nav_menu(array('theme_location'=>'mobile','menu_class'=>'nav container-inner group','container'=>'','menu_id' => '','fallback_cb'=> false)); ?></div>											
				</nav><!--/#nav-mobile-->
			<?php endif; ?>
			
		</header><!--/#header-->

		<div class="sidebar s2">
			<?php if ( get_theme_mod( 'header-search', 'on' ) == 'on' ): ?>
				<div class="toggle-search"><i class="fas fa-search"></i></div>
				<div class="search-expand">
					<div class="search-expand-inner">
						<?php get_search_form(); ?>
					</div>
				</div>
			<?php endif; ?>
			<?php if ( get_theme_mod( 'header-social', 'on' ) == 'on' ): ?>
				<?php readline_social_links() ; ?>
			<?php endif; ?>
		</div>
		<div class="clear"></div>

		<div id="page">
		
		<?php get_template_part('inc/page-title'); ?>
		<?php get_template_part('inc/featured'); ?>
		<div class="main-inner group">
